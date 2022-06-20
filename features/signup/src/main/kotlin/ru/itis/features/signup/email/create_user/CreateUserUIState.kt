package ru.itis.features.signup.email.create_user

import ru.itis.core.ui.common.FieldCorrectnessCheck

/**
 * Created by Iskandar on 25.03.2022.
 */

internal data class CreateUserUIState(
    val inputUserName: InputUserName = InputUserName(),
    val inputPassword: InputPassword = InputPassword(),
    val snackBar: SnackBar = SnackBar(),
    val email: String = "",
    val couldNavigate: Boolean = false,
    val internetAvailable: Boolean = true,
    val isLoading: Boolean = false,
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

    internal data class SnackBar(
        val show: Boolean = false,
        val message: String = "",
        val isError: Boolean = true
    )
}
