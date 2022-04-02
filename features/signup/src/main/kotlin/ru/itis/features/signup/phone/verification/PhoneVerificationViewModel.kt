package ru.itis.features.signup.phone.verification

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.IPhoneSignUpUseCase
import javax.inject.Inject

/**
 * Created by Iskandar on 23.03.2022.
 */

class PhoneVerificationViewModel(
    private val phoneSignUpUseCase: IPhoneSignUpUseCase,
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {
    private val _phoneVerificationUIState = MutableStateFlow(PhoneVerificationScreenUIState())
    val phoneVerificationUIState = _phoneVerificationUIState.asStateFlow()

    fun onCodeChange(code: String) {
        _phoneVerificationUIState.update {
            it.copy(
                inputCode = PhoneVerificationScreenUIState.InputCode(
                    code = code,
                    isFieldEnabled = true
                )
            )
        }

    }

    fun onRequestClick(activity: Activity) {
        _phoneVerificationUIState.update {
            it.copy(
                requestNewCode = PhoneVerificationScreenUIState.NewCode(
                    clicked = true,
                    inProcess = true,
                    newCodeReceived = false
                )
            )
        }
        viewModelScope.launch(dispatchersProvider.IO) {
            phoneSignUpUseCase.resendCode(activity, "")
        }
    }


    class PhoneVerificationViewModelFactory @Inject constructor(
        private val signUpUseCase: IPhoneSignUpUseCase,
        private val dispatchersProvider: DispatchersProvider
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PhoneVerificationViewModel(signUpUseCase, dispatchersProvider) as T
        }
    }
}
