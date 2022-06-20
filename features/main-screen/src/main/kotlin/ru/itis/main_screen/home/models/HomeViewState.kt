package ru.itis.main_screen.home.models

/**
 * Created by Iskandar on 08.04.2022.
 */

sealed class HomeViewState {
    object Loading : HomeViewState()
    object Error : HomeViewState()
    data class Display(
        val items: List<HomeAdvModel> = emptyList(),
        val isNetworkAvailable: Boolean = true
    ) : HomeViewState()

    object NoInternet : HomeViewState()
}
