package ru.itis.features.signin

/**
 * Created by Iskandar on 12.03.2022.
 */

data class SignInUIState(
    val inputEmail: InputEmailField = InputEmailField(),
    val inputPassword: InputPasswordField = InputPasswordField(),
    val networkError: Boolean = false
) {

    data class InputEmailField(
        val email: String = "",
        val isFieldEnabled: Boolean = true,
    )

    data class InputPasswordField(
        val password: String = "",
        val isFieldEnabled: Boolean = true,
        val isPasswordVisible: Boolean = false
    )
}
