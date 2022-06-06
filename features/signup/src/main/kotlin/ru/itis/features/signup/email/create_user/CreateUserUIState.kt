package ru.itis.features.signup.email.create_user

import ru.itis.core.ui.common.FieldCorrectnessCheck

/**
 * Created by Iskandar on 25.03.2022.
 */

internal data class CreateUserUIState(
    val inputUserName: InputUserName = InputUserName(),
    val inputPassword: InputPassword = InputPassword(),
    val email: String = "",
    val couldNavigate: Boolean = false
) {

    internal data class InputUserName(
        val name: String = "",
        val isFieldEnabled: Boolean = true,
        val showError: FieldCorrectnessCheck = FieldCorrectnessCheck.None
    )

    internal data class InputPassword(
        val password: String = "",
        val isFieldEnabled: Boolean = true,
        val showError: FieldCorrectnessCheck = FieldCorrectnessCheck.None
    )

}
