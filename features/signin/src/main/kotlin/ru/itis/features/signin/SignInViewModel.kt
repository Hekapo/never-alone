package ru.itis.features.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.models.User
import ru.itis.core.domain.usecase.IDatabaseUseCase
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
    private val databaseUseCase: IDatabaseUseCase,
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
        signInUseCase.signInWithGoogleState.onEach(this::signInWithGoogleState)
            .launchIn(viewModelScope)
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

    fun onSignInWithGoogle(token: String) {
        viewModelScope.launch(dispatchersProvider.IO) {
            signInUseCase.signInWithGoogle(token)
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

    // TODO: handle errors
    private fun signInWithGoogleState(signInState: ResultState<User, String>) {
        when (signInState) {
            is ResultState.None -> {}
            is ResultState.Error -> {
                onError(message = "Err")
            }
            is ResultState.Success -> {
                onSuccessWithGoogle(user = signInState.data)
            }
            is ResultState.InProcess -> {
                inProcess()
            }
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
        _signInUIState.update { it.copy(isLoading = false, couldNavigate = true) }
    }

    private fun onSuccessWithGoogle(user: User) {
        viewModelScope.launch(dispatchersProvider.IO) {
            Log.e("DEBUG", user.toString())
            databaseUseCase.addUser(user)
        }
        _signInUIState.update { it.copy(isLoading = false, couldNavigate = true) }

    }

    fun setCouldNotNavigate() {
        _signInUIState.update { it.copy(couldNavigate = true) }
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
                ),
                couldNavigate = false
            )
        }
    }

    fun hideSnackbar() {
        _signInUIState.update {
            it.copy(snackBar = SignInUIState.SnackBar(show = false))
        }
    }

    class SignInViewModelFactory @Inject constructor(
        private val signInUseCase: ISignInUseCase,
        private val databaseUseCase: IDatabaseUseCase,
        private val dispatchersProvider: DispatchersProvider,
        private val networkListener: NetworkListener
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SignInViewModel(
                signInUseCase,
                databaseUseCase,
                dispatchersProvider,
                networkListener
            ) as T
        }
    }
}
