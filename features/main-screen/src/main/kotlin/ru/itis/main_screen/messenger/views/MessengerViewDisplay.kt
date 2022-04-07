package ru.itis.main_screen.messenger.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme
import ru.itis.main_screen.messenger.models.MessengerViewState

/**
 * Created by Iskandar on 04.04.2022.
 */

@Composable
internal fun MessengerViewDisplay(
    viewState: MessengerViewState.Display,
    onChatClicked: (MessengerChatModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn {
            viewState.items.forEach { chatItem ->
                item {
                    OneChatItem(
                        onChatClick = { onChatClicked(chatItem) },
                        model = chatItem
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MessengerViewDisplayPreview() {
    MessengerViewDisplay(
        viewState = MessengerViewState.Display(
            items = listOf(
                MessengerChatModel(
                    userName = "Iskandar",
                    lastMessage = "Hi",
                    icon = R.drawable._7163859
                ),

                MessengerChatModel(
                    userName = "Mike",
                    lastMessage = "LOL",
                    icon = R.drawable.ic_loading
                )
            )
        ),
        onChatClicked = {}
    )
}
