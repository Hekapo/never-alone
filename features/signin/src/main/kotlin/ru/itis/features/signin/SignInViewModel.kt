package ru.itis.features.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.ISignInUseCase
import ru.itis.core.domain.viewstates.ResultState
import ru.itis.core.network.NetworkListener
import ru.itis.core.ui.common.checkPasswordLength
import ru.itis.core.ui.common.isEmailCorrect
import javax.inject.Inject

/**
 * Created by Iskandar on 12.03.2022.
 */

internal class SignInViewModel(
    private val signInUseCase: ISignInUseCase,
    private val dispatchersProvider: DispatchersProvider,
    networkListener: NetworkListener
) : ViewModel() {

    private val _signInUIState = MutableStateFlow(SignInUIState())
    val signInUIState = _signInUIState.asStateFlow()

    init {
        networkListener.networkState
            .distinctUntilChanged()
            .onEach(this::onNetwork)
            .flowOn(dispatchersProvider.IO)
            .launchIn(viewModelScope)

        signInUseCase.signInState.onEach(this::signInState).launchIn(viewModelScope)
    }

    private fun onNetwork(isAvailable: Boolean) {
        _signInUIState.update { it.copy(internetAvailable = isAvailable) }
    }

    fun onSignInClick() {
        viewModelScope.launch(dispatchersProvider.IO) {
            val email = _signInUIState.value.inputEmail.email
            val password = _signInUIState.value.inputPassword.password
            signInUseCase.trySignIn(email, password)
        }
    }

    fun onEmailChanged(email: String) {
        _signInUIState.update {
            it.copy(
                inputEmail = SignInUIState.InputEmailField(
                    email = email,
                    isFieldEnabled = true,
                    showError = email.isEmailCorrect()
                )
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _signInUIState.update {
            it.copy(
                inputPassword = SignInUIState.InputPasswordField(
                    password = password,
                    isFieldEnabled = true,
                    showError = password.checkPasswordLength()
                )
            )
        }
    }

    private fun signInState(signInState: ResultState<String, String>) {
        when (signInState) {
            is ResultState.None -> {}
            is ResultState.Error -> {
                onError(message = signInState.message!!)
            }
            is ResultState.Success -> {
                onSuccess()
            }
            is ResultState.InProcess -> {
                inProcess()
            }
        }
    }

    private fun onSuccess() {
        _signInUIState.update { it.copy(isLoading = false) }
    }

    private fun inProcess() {
        _signInUIState.update {
            it.copy(isLoading = true)
        }
    }

    private fun onError(message: String) {
        _signInUIState.update {
            it.copy(
                isLoading = false,
                snackBar = SignInUIState.SnackBar(
                    show = true,
                    message = message,
                    isError = true
                )
            )
        }
    }

    class SignInViewModelFactory @Inject constructor(
        private val signInUseCase: ISignInUseCase,
        private val dispatchersProvider: DispatchersProvider,
        private val networkListener: NetworkListener
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SignInViewModel(signInUseCase, dispatchersProvider, networkListener) as T
        }
    }
}
