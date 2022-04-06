package ru.itis.main_screen.messenger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.itis.core.EventHandler
import ru.itis.main_screen.messenger.models.MessengerEvent
import ru.itis.main_screen.messenger.models.MessengerViewState
import ru.itis.main_screen.messenger.views.MessengerChatModel
import javax.inject.Inject

/**
 * Created by Iskandar on 04.04.2022.
 */

internal class MessengerViewModel() : ViewModel(), EventHandler<MessengerEvent> {

    private val _messengerViewState: MutableStateFlow<MessengerViewState> =
        MutableStateFlow(MessengerViewState.Loading)
    val messengerViewState: StateFlow<MessengerViewState> = _messengerViewState

    override fun obtainEvent(event: MessengerEvent) {
        when (val currentViewState = _messengerViewState.value) {
            is MessengerViewState.Loading -> reduce(event, currentViewState)
            is MessengerViewState.Error -> reduce(event, currentViewState)
            is MessengerViewState.NoChats -> reduce(event, currentViewState)
            is MessengerViewState.Display -> reduce(event, currentViewState)
        }
    }

    private fun reduce(event: MessengerEvent, currentViewState: MessengerViewState.Loading) {
        when (event) {
            is MessengerEvent.EnterScreen -> {
                fetchChats()
            }
            else -> throw NotImplementedError("Unknown $event for $currentViewState")
        }
    }

    private fun reduce(event: MessengerEvent, currentViewState: MessengerViewState.NoChats) {
        when (event) {
            MessengerEvent.ReloadScreen -> fetchChats()
            MessengerEvent.ReloadScreen -> {}
            is MessengerEvent.OnChatClick -> {}
            else -> throw NotImplementedError("Unknown $event for $currentViewState")
        }
    }

    private fun reduce(event: MessengerEvent, currentViewState: MessengerViewState.Display) {
        when (event) {
            MessengerEvent.EnterScreen -> fetchChats()
            else -> throw NotImplementedError("Unknown $event for $currentViewState")
        }
    }

    private fun reduce(event: MessengerEvent, currentViewState: MessengerViewState.Error) {
        when (event) {
            MessengerEvent.EnterScreen -> {
                fetchChats()
            }
            else -> throw NotImplementedError("Unknown $event for $currentViewState")
        }
    }

    private fun fetchChats() {
        viewModelScope.launch {
            _messengerViewState.compareAndSet(_messengerViewState.value, MessengerViewState.Loading)

            delay(1000)
            _messengerViewState.compareAndSet(
                _messengerViewState.value,
                MessengerViewState.Display(
                    listOf(
                        MessengerChatModel(
                            "Iskandar",
                            "last",
                            icon = ru.itis.core.ui.R.drawable._7163859
                        ),
                        MessengerChatModel(
                            "Iskandar2",
                            "last",
                            icon = ru.itis.core.ui.R.drawable._7163859
                        ),
                    )
                )
            )

        }
    }

    internal class MessengerViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MessengerViewModel() as T
        }
    }
}
