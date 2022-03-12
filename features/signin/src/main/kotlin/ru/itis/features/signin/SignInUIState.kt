package ru.itis.features.signin

/**
 * Created by Iskandar on 12.03.2022.
 */

sealed class SignInUIState {
    class InputEmail(val email: String = "") : SignInUIState()
    class InputPassword(val password: String = "") : SignInUIState()
    class InputPasswordVisibility(val visible: Boolean = false) : SignInUIState()
    class NetworkError(val error: Boolean = false) : SignInUIState()
}
