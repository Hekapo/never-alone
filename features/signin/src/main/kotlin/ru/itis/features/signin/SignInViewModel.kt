package ru.itis.features.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.ISignInUseCase
import ru.itis.core.domain.viewstates.SignInState
import javax.inject.Inject

/**
 * Created by Iskandar on 12.03.2022.
 */

internal class SignInViewModel(
    private val signInUseCase: ISignInUseCase,
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {
    private val _signInUIState = MutableStateFlow(SignInUIState())
    val signInUIState = _signInUIState.asStateFlow()

    init {
        signInUseCase.signInState.onEach(this::signInState).launchIn(viewModelScope)
    }

    fun onSignInClick() {
        viewModelScope.launch(dispatchersProvider.IO) {
            val email = _signInUIState.value.inputEmail.email
            val password = _signInUIState.value.inputPassword.password
            signInUseCase.trySignIn(email, password)
        }

//        _signInUIState.update {
//            it.copy(
//                inputEmail = SignInUIState.InputEmailField(isFieldEnabled = false),
//                inputPassword = SignInUIState.InputPasswordField(isFieldEnabled = false)
//            )
//        }

    }

    fun onEmailChanged(email: String) {
        _signInUIState.update {
            it.copy(
                inputEmail = SignInUIState.InputEmailField(
                    email = email,
                    isFieldEnabled = true
                )
            )
        }
        Log.e("TAG", _signInUIState.value.inputEmail.email)

    }

    fun onPasswordChanged(password: String) {
        _signInUIState.update {
            it.copy(
                inputPassword = SignInUIState.InputPasswordField(
                    password = password,
                    isFieldEnabled = true
                )
            )
        }
        Log.e("TAG", _signInUIState.value.inputPassword.password)

    }

    private fun signInState(signInState: SignInState) {
        when (signInState) {
            is SignInState.SignInStateNone -> {}
            is SignInState.SignInStateInProcess -> run { signInLoading() }
            is SignInState.SignInStateSuccess -> run { signInComplete() }
            is SignInState.SignInStateError -> run { signInError() }
        }
    }

    private fun signInComplete() {
        _signInUIState.update {
            it.copy(
                signInProcess = SignInUIState.SignInProcess(
                    signInSuccess = true,
                    signInLoading = false,
                    signInError = false
                )
            )
        }
    }

    private fun signInLoading() {
        _signInUIState.update {
            it.copy(
                signInProcess = SignInUIState.SignInProcess(
                    signInSuccess = false,
                    signInLoading = true,
                    signInError = false
                )
            )
        }
    }

    private fun signInError() {
        _signInUIState.update {
            it.copy(
                signInProcess = SignInUIState.SignInProcess(
                    signInSuccess = false,
                    signInLoading = false,
                    signInError = true
                ),
                inputEmail = SignInUIState.InputEmailField(
                    isFieldEnabled = true
                )
            )
        }
    }


    class SignInViewModelFactory @Inject constructor(
        private val signInUseCase: ISignInUseCase,
        private val dispatchersProvider: DispatchersProvider
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SignInViewModel(signInUseCase, dispatchersProvider) as T
        }
    }
}
