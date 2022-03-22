package ru.itis.core.domain.viewstates

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */

sealed class PhoneSignUpState {
    object None : PhoneSignUpState()
    object InProcess : PhoneSignUpState()
    object CodeSent : PhoneSignUpState()
    object Success : PhoneSignUpState()
    object Error : PhoneSignUpState()
    class InvalidCredential(val message: String?) : PhoneSignUpState()
    class TooManyRequests(val message: String?) : PhoneSignUpState()
}
