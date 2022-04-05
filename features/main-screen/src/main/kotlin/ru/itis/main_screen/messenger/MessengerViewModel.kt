package ru.itis.main_screen.messenger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.itis.core.EventHandler
import ru.itis.main_screen.messenger.models.MessengerEvent
import ru.itis.main_screen.messenger.models.MessengerViewState
import javax.inject.Inject

/**
 * Created by Iskandar on 04.04.2022.
 */

class MessengerViewModel() : ViewModel(), EventHandler<MessengerEvent> {

    private val _messengerViewState: MutableStateFlow<MessengerViewState> =
        MutableStateFlow(MessengerViewState.Loading)
    val messengerViewState: StateFlow<MessengerViewState> = _messengerViewState

    override fun obtainEvent(event: MessengerEvent) {
        when (val currentViewState = _messengerViewState.value) {
            MessengerViewState.Loading -> reduce(event, currentViewState)
            MessengerViewState.Error -> reduce(event, currentViewState)
            MessengerViewState.NoChats -> reduce(event, currentViewState)
            is MessengerViewState.Display -> reduce(event, currentViewState)
        }
    }

    private fun reduce(event: MessengerEvent, currentViewState: MessengerViewState) {
        when (event) {
            MessengerEvent.EnterScreen -> {}
            MessengerEvent.ReloadScreen -> {}
            is MessengerEvent.OnChatClick -> {}
        }
    }

    class MessengerViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MessengerViewModel() as T
        }
    }
}
