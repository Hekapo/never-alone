package ru.itis.main_screen.messenger.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme
import ru.itis.main_screen.messenger.models.MessengerViewState

/**
 * Created by Iskandar on 04.04.2022.
 */

@Composable
internal fun MessengerViewDisplay(
    viewState: MessengerViewState.Display,
    onChatClicked: () -> Unit
) {
    val scrollState = rememberScrollState()

    val sr = rememberSwipeRefreshState(isRefreshing = false)
    SwipeRefresh(state = sr,onRefresh = { sr.isRefreshing = true}){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.backgroundPrimary)
            ,
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn() {
                viewState.items.forEach { chatItem ->
                    item {
                        OneChatItem(
                            onChatClick = onChatClicked,
                            model = chatItem
                        )
                    }
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
                    userName = "Mile",
                    lastMessage = "LOL",
                    icon = R.drawable.ic_loading
                )
            )
        ),
        onChatClicked = {}
    )
}
