package ru.itis.features.signin

/**
 * Created by Iskandar on 12.03.2022.
 */

data class SignInUIState(
    val inputEmail: InputEmailField = InputEmailField(),
    val inputPassword: InputPasswordField = InputPasswordField(),
    val signInProcess: SignInProcess = SignInProcess(),
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

    data class SignInProcess(
        val signInSuccess: Boolean = false,
        val signInLoading: Boolean = false,
        val signInError: Boolean = false
    )
}
