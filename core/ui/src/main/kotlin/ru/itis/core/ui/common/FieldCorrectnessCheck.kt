package ru.itis.core.ui.common

sealed class FieldCorrectnessCheck {
    object None : FieldCorrectnessCheck()
    data class Success(val data: String) : FieldCorrectnessCheck()
    data class Error(val message: Int) : FieldCorrectnessCheck()
}
