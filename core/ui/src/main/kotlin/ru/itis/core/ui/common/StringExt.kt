package ru.itis.core.ui.common

import ru.itis.core.ui.R

fun String.isPhoneNumberCorrect(): FieldCorrectnessCheck {
    if (this.isBlank())
        return FieldCorrectnessCheck.Error(message = R.string.input_field_empty_error)

    val patternWithoutPlus = "^7(\\(9\\d{2}\\)|9\\d{2})-?\\d{3}-?\\d{2}-?\\d{2}$".toRegex()
    val patternWithPlus = "^(\\+7|8)(\\(9\\d{2}\\)|9\\d{2})-?\\d{3}-?\\d{2}-?\\d{2}$".toRegex()
    val strWithoutSpaces = this.replace(" ", "")

    if (strWithoutSpaces.contains(patternWithoutPlus)) {
        return FieldCorrectnessCheck.Success(
            data = "+${
                strWithoutSpaces.replace(
                    "[^\\d]".toRegex(),
                    ""
                )
            }"
        )
    }
    if (strWithoutSpaces.contains(patternWithPlus)) {
        return FieldCorrectnessCheck.Success(
            data = strWithoutSpaces.replace("[^\\d]".toRegex(), "")
                .replace("^([87])".toRegex(), "+7")
        )
    }
    return FieldCorrectnessCheck.Error(message = R.string.check_is_phone_correct_error)
}

fun String.isEmailCorrect(): FieldCorrectnessCheck {
    if (this.isBlank())
        return FieldCorrectnessCheck.Error(message = R.string.input_field_empty_error)

    val emailPattern = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex()

    val strWithoutSpaces = this.replace(" ", "")
    if (strWithoutSpaces.contains(emailPattern)) {
        return FieldCorrectnessCheck.Success(
            data = strWithoutSpaces
        )
    }
    return FieldCorrectnessCheck.Error(message = R.string.check_is_email_correct_error)

}

fun String.checkPasswordLength(): FieldCorrectnessCheck {

    if (this.isBlank() || this.isEmpty()) {
        return FieldCorrectnessCheck.Error(message = R.string.input_field_empty_error)
    }

    return if (this.trim().length >= 6) {
        FieldCorrectnessCheck.Success(data = "")
    } else {
        FieldCorrectnessCheck.Error(message = R.string.short_password_error)
    }
}