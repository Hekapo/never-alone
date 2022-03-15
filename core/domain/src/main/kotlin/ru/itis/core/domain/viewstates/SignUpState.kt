package ru.itis.core.domain.viewstates

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */

sealed class SignUpState {
    object SignUpStateNone : SignUpState()
    object SignUpStateInProcess : SignUpState()
    object SignUpStateSuccess : SignUpState()
    class SignUpStateError(val message: String?) : SignUpState()
}
