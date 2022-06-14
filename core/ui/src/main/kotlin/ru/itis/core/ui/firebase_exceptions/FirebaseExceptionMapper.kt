package ru.itis.core.ui.firebase_exceptions

import ru.itis.core.ui.R

/**
 * Copyright (c) 14.06.2022 Created by Iskandar
 */

fun String.authCodeMap(): Int {
    return when (this.trim()) {
        "ERROR_EMAIL_ALREADY_IN_USE" -> {
            R.string.email_already_in_use
        }
        else -> {
            R.string.error
        }
    }
}
