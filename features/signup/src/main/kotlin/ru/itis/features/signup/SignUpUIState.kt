package ru.itis.features.signup

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */

internal data class SignUpUIState(
    val inputEmail: InputEmailField = InputEmailField(),
    val signUpProcess: SignUpProcess = SignUpProcess(),
    val networkError: Boolean = false
) {

    data class InputEmailField(
        val email: String = "",
        val isFieldEnabled: Boolean = true,
    )

    data class SignUpProcess(
        val signInSuccess: Boolean = false,
        val signInLoading: Boolean = false,
        val signInError: Boolean = false
    )
}
