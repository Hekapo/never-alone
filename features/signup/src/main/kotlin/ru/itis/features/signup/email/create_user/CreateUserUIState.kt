package ru.itis.features.signup.email.create_user

/**
 * Created by Iskandar on 25.03.2022.
 */

data class CreateUserUIState(
    val inputUserName: InputUserName = InputUserName(),
    val inputPassword: InputPassword = InputPassword()

) {

    data class InputUserName(
        val name: String = "",
        val isFieldEnabled: Boolean = true
    )

    data class InputPassword(
        val password: String = "",
        val isFieldEnabled: Boolean = true
    )

}
