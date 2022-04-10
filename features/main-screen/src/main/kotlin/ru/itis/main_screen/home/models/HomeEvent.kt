package ru.itis.main_screen.home.models

/**
 * Created by Iskandar on 08.04.2022.
 */

sealed class HomeEvent {
    object EnterScreen : HomeEvent()
    object ReloadScreen : HomeEvent()
    data class OnAdvClick(val avd: Long) : HomeEvent()
}
