package ru.itis.main_screen.messenger.models

/**
 * Created by Iskandar on 04.04.2022.
 */

sealed class MessengerEvent {
    object EnterScreen : MessengerEvent()
    object ReloadScreen : MessengerEvent()
    data class OnChatClick(val chat: Long) : MessengerEvent()
}
