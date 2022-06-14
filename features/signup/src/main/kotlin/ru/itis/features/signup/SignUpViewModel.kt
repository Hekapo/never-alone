package ru.itis.features.signup

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.IPhoneSignUpUseCase
import ru.itis.core.domain.viewstates.PhoneSignUpState
import ru.itis.core.network.NetworkListener
import ru.itis.core.ui.common.isEmailCorrect
import ru.itis.core.ui.common.isPhoneNumberCorrect
import javax.inject.Inject

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */

internal class SignUpViewModel(
    private val phoneSignUpUseCase: IPhoneSignUpUseCase,
    private val dispatchersProvider: DispatchersProvider,
    networkListener: NetworkListener
) : ViewModel() {

    private val _signUpUIState = MutableStateFlow(SignUpUIState())
    val signUpUIState = _signUpUIState.asStateFlow()

    init {

        networkListener.networkState
            .distinctUntilChanged()
            .onEach(this::onNetwork)
            .flowOn(dispatchersProvider.IO)
            .launchIn(viewModelScope)

        phoneSignUpUseCase.phoneSignUpState.onEach(this::signUpState).launchIn(viewModelScope)
    }

    private fun onNetwork(isAvailable: Boolean) {
        _signUpUIState.update {
            it.copy(networkAvailable = isAvailable)
        }
    }

    fun onTabSelected(tabIndex: Int) = _signUpUIState.update { it.copy(activeTab = tabIndex) }

    private fun signUpState(phoneSignUpState: PhoneSignUpState) {
        when (phoneSignUpState) {
            is PhoneSignUpState.None -> run {}
            is PhoneSignUpState.InProcess -> run { inProcess() }
            is PhoneSignUpState.CodeSent -> run { codeSent() }
            is PhoneSignUpState.VerificationComplete -> run {}
            is PhoneSignUpState.VerificationInProcess -> run {}
            is PhoneSignUpState.VerificationFailure -> run {}
            is PhoneSignUpState.Error -> run {}
            is PhoneSignUpState.InvalidCredential -> run {}
            is PhoneSignUpState.TooManyRequests -> run {}
        }

    }

    fun onSendCodeClick(activity: Activity) {
//        _signUpUIState.update {
//            it.copy()
//        }
        viewModelScope.launch(dispatchersProvider.IO) {
            val phone = _signUpUIState.value.inputPhone.phone
            if (phone.isNotEmpty() && phone.isNotBlank()) {
                phoneSignUpUseCase.trySignUpWithPhone(activity, "+$phone")
            } else {

            }
//            phoneSignUpUseCase.verifyPhoneNumberWithCode("")
        }

    }

    fun onPhoneChange(phone: String) {
        _signUpUIState.update {
            it.copy(
                inputPhone =
                SignUpUIState.InputPhoneField(
                    phone = phone,
                    isError = phone.isPhoneNumberCorrect()
                )
            )
        }
    }

    fun onEmailChange(email: String) {
        _signUpUIState.update {
            it.copy(
                inputEmail = SignUpUIState.InputEmailField(
                    email = email,
                    isError = email.isEmailCorrect()
                )
            )
        }
    }

    private fun inProcess() {
        _signUpUIState.update {
            it.copy(
                inputPhone = SignUpUIState.InputPhoneField(isFieldEnabled = false),
                isLoading = true
            )
        }
    }

    private fun codeSent() {
        _signUpUIState.update {
            it.copy(isLoading = false)
        }
    }

    class SignUpViewModelFactory @Inject constructor(
        private val signUpUseCase: IPhoneSignUpUseCase,
        private val dispatchersProvider: DispatchersProvider,
        private val networkListener: NetworkListener
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SignUpViewModel(signUpUseCase, dispatchersProvider, networkListener) as T
        }
    }
}
