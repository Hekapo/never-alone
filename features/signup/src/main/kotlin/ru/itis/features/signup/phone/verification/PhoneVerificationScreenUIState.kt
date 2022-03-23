package ru.itis.features.signup.phone.verification

/**
 * Created by Iskandar on 23.03.2022.
 */

data class PhoneVerificationScreenUIState(
    val requestNewCode: NewCode = NewCode(),
    val inputCode: InputCode = InputCode(),
) {
    data class NewCode(
        val clicked: Boolean = false,
        val inProcess: Boolean = false,
        val newCodeReceived: Boolean = false
    )

    data class InputCode(
        val code: String = "",
        val isFieldEnabled: Boolean = true
    )

}
