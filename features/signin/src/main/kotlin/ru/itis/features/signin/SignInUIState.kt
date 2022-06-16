package ru.itis.features.signin

import ru.itis.core.ui.common.FieldCorrectnessCheck

/**
 * Created by Iskandar on 12.03.2022.
 */

data class SignInUIState(
    val inputEmail: InputEmailField = InputEmailField(),
    val inputPassword: InputPasswordField = InputPasswordField(),
    val snackBar: SnackBar = SnackBar(),
    val internetAvailable: Boolean = true,
    val isLoading: Boolean = false,
    val couldNavigate: Boolean = false
) {

    data class InputEmailField(
        val email: String = "",
        val isFieldEnabled: Boolean = true,
        val showError: FieldCorrectnessCheck = FieldCorrectnessCheck.None
    )

    data class InputPasswordField(
        val password: String = "",
        val isFieldEnabled: Boolean = true,
        val showError: FieldCorrectnessCheck = FieldCorrectnessCheck.None
    )

    data class SnackBar(
        val show: Boolean = false,
        val message: String = "",
        val isError: Boolean = true
    )
}
