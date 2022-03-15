package ru.itis.features.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
