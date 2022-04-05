package ru.itis.main_screen.messenger.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.R
import ru.itis.core.ui.components.ImageTopAppBar
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary)
    ) {
        ImageTopAppBar(
            centerImageVector = ImageVector.vectorResource(id = R.drawable.leaves),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.title_messenger),
                style = AppTheme.typography.text28R
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {

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

@Preview
@Composable
fun MessengerViewDisplayPreview() {
    MessengerViewDisplay(
        viewState = MessengerViewState.Display(
            items = listOf(
                MessengerChatModel(
                    userName = "Iskandar",
                    lastMessage = "Hi",
                    icon = painterResource(id = R.drawable._7163859)
                ),

                MessengerChatModel(
                    userName = "Mile",
                    lastMessage = "LOL",
                    icon = painterResource(id = R.drawable.ic_loading)
                )
            )
        ),
        onChatClicked = {}
    )
}
