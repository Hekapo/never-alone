package ru.itis.user_form

data class UserFormUIState(
    val snackBar: SnackBar = SnackBar(),
    val isLoading: Boolean = false,
    val networkAvailable: Boolean = true
) {
    data class SnackBar(
        val show: Boolean = false,
        val message: String = "",
        val isError: Boolean = true
    )
}
