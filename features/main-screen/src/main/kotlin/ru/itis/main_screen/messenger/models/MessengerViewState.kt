package ru.itis.main_screen.messenger.models

import ru.itis.main_screen.messenger.views.MessengerChatModel

/**
 * Created by Iskandar on 04.04.2022.
 */
sealed class MessengerViewState {
    object Loading : MessengerViewState()
    object Error : MessengerViewState()
    data class Display(
        val items: List<MessengerChatModel>,
    ) : MessengerViewState()

    object NoChats : MessengerViewState()
    object NoInternet : MessengerViewState()
}
