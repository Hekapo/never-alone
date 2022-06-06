package ru.itis.features.signup

import ru.itis.core.ui.common.FieldCorrectnessCheck
import ru.itis.features.signup.utils.Constants.TAB_PHONE

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */

data class SignUpUIState(
    val inputEmail: InputEmailField = InputEmailField(),
    val inputPhone: InputPhoneField = InputPhoneField(),
    val signUpProcess: SignUpProcess = SignUpProcess(),
    val activeTab: Int = TAB_PHONE,
    val networkError: Boolean = false,
    val isLoading: Boolean = false
) {

    data class InputEmailField(
        val email: String = "",
        val isFieldEnabled: Boolean = true,
        val isError: FieldCorrectnessCheck = FieldCorrectnessCheck.None
    )

    data class InputPhoneField(
        val phone: String = "",
        val isFieldEnabled: Boolean = true,
        val isError: FieldCorrectnessCheck = FieldCorrectnessCheck.None
    )

    data class SignUpProcess(
        val signUpSuccess: Boolean = false,
        val signUpLoading: Boolean = false,
        val signUpError: Boolean = false
    )
}
