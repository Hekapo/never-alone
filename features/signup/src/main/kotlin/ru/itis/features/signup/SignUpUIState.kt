package ru.itis.features.signup

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */

data class SignUpUIState(
    val inputEmail: InputEmailField = InputEmailField(),
    val signUpProcess: SignUpProcess = SignUpProcess(),
    val networkError: Boolean = false
) {

    data class InputEmailField(
        val email: String = "",
        val isFieldEnabled: Boolean = true,
    )

    data class SignUpProcess(
        val signUpSuccess: Boolean = false,
        val signUpLoading: Boolean = false,
        val signUpError: Boolean = false
    )
}
