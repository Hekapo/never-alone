package ru.itis.features.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.ISignInUseCase
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

    private fun onSignInClick() {
        _signInUIState.update {
            it.copy(
                inputEmail = SignInUIState.InputEmailField(isFieldEnabled = false),
                inputPassword = SignInUIState.InputPasswordField(isFieldEnabled = false)
            )
        }

        viewModelScope.launch(dispatchersProvider.IO) {
            val email = _signInUIState.value.inputEmail.email
            val password = _signInUIState.value.inputPassword.password
            signInUseCase.trySignIn(email, password)
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
