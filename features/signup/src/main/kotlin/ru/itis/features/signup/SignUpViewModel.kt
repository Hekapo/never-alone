package ru.itis.features.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.ISignUpUseCase
import ru.itis.core.domain.viewstates.SignUpState
import javax.inject.Inject

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */


internal class SignUpViewModel(
    private val signUpUseCase: ISignUpUseCase,
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {
    private val _signUpUIState = MutableStateFlow(SignUpUIState())
    val signUpUIState = _signUpUIState.asStateFlow()

    init {
        signUpUseCase.signUpState.onEach(this::signUpState).launchIn(viewModelScope)
    }

    private fun signUpState(signUpState: SignUpState) {
        when (signUpState) {
            is SignUpState.SignUpStateNone -> run {}
            is SignUpState.SignUpStateInProcess -> run {}
            is SignUpState.SignUpStateSuccess -> run {}
            is SignUpState.SignUpStateError -> run {}
        }

    }

    fun onEmailChange(email: String) {
        _signUpUIState.update {
            it.copy(
                inputEmail = SignUpUIState.InputEmailField(
                    email = email,
                    isFieldEnabled = true
                )
            )
        }
        Log.e("TAG", _signUpUIState.value.inputEmail.email)

    }

    class SignUpViewModelFactory @Inject constructor(
        private val signUpUseCase: ISignUpUseCase,
        private val dispatchersProvider: DispatchersProvider
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SignUpViewModel(signUpUseCase, dispatchersProvider) as T
        }
    }
}
