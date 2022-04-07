package ru.itis.main_screen.messenger

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.core.ui.views.ViewLoading
import ru.itis.main_screen.messenger.models.MessengerEvent
import ru.itis.main_screen.messenger.models.MessengerViewState
import ru.itis.main_screen.messenger.views.MessengerViewDisplay
import ru.itis.main_screen.messenger.views.MessengerViewError
import ru.itis.main_screen.messenger.views.MessengerViewNoChats

/**
 * Created by Iskandar on 02.04.2022.
 */

@Composable
fun MessengerScreenRoute(
    deps: MessengerDeps
) {

    val messengerComponentViewModel = viewModel<MessengerComponentViewModel>(
        factory = MessengerComponentViewModelFactory(deps)
    )

    val messengerViewModel = viewModel<MessengerViewModel>(
        factory = messengerComponentViewModel.messengerComponentViewModel.messengerViewModel
    )

    val viewState = messengerViewModel.messengerViewState.collectAsState()

    when (val state = viewState.value) {
        MessengerViewState.Loading -> ViewLoading()
        MessengerViewState.NoChats -> MessengerViewNoChats()
        MessengerViewState.Error -> MessengerViewError("")
        is MessengerViewState.Display -> MessengerViewDisplay(
            viewState = state,
            onChatClicked = {
                messengerViewModel.obtainEvent(MessengerEvent.OnChatClick(chat = 1L)) // TODO
            }
        )
        MessengerViewState.NoInternet -> {}
    }

    LaunchedEffect(key1 = viewState, block = {
        messengerViewModel.obtainEvent(event = MessengerEvent.EnterScreen)
    })
}
