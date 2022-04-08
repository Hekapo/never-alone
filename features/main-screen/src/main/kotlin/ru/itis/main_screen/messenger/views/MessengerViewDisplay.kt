package ru.itis.main_screen.messenger.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import ru.itis.core.ui.R
import ru.itis.core.ui.components.LoadingAnimation
import ru.itis.core.ui.theme.AppTheme
import ru.itis.main_screen.messenger.models.MessengerViewState
import kotlin.math.roundToInt

/**
 * Created by Iskandar on 04.04.2022.
 */

@Composable
internal fun MessengerViewDisplay(
    viewState: MessengerViewState.Display,
    onChatClicked: (MessengerChatModel) -> Unit
) {
    val maxTopOffset = 200f
    val refreshPaddingTop = remember { mutableStateOf(0f) }
    var isRefreshing by remember { mutableStateOf(false) }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)
    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            delay(1000L)
            isRefreshing = false
        }
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            isRefreshing = true
        },
        indicator = { state, triggerDp ->
//                            SwipeRefreshIndicator(state = state, refreshTriggerDistance = triggerDp)

            if (state.isRefreshing) {
//                LoadingAnimation()

            } else {
//                Log.e("DEBUG", "${state.indicatorOffset} ${state.isSwipeInProgress} ${state.isRefreshing} ")
                val triggerPx = with(LocalDensity.current) { triggerDp.toPx() }
                val progress = (state.indicatorOffset / triggerPx).coerceIn(0f, 1f)
                refreshPaddingTop.value = progress * maxTopOffset
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.backgroundPrimary)
                .offset { IntOffset(0, refreshPaddingTop.value.roundToInt()) }
        ) {
            viewState.items.forEach { chatItem ->
                item {
                    OneChatItem(
                        onChatClick = { },
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
