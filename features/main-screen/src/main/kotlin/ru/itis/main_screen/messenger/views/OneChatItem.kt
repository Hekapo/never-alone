package ru.itis.main_screen.messenger.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.R
import ru.itis.core.ui.components.repeatingClickable
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 04.04.2022.
 */

data class MessengerChatModel(
    val userName: String,
    val lastMessage: String,
    val icon: Int
)

@Composable
internal fun OneChatItem(
    model: MessengerChatModel,
    onChatClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.backgroundPrimary)
            .repeatingClickable(onClick = onChatClick)
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                painter = painterResource(id = model.icon),
                contentDescription = "Image"
            )
        }

        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = model.userName,
                color = AppTheme.colors.textHighEmphasis,
                style = AppTheme.typography.text20M
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = model.lastMessage,
                color = AppTheme.colors.textMediumEmphasis,
                style = AppTheme.typography.text16R
            )
        }
    }
}

@Preview()
@Composable
fun OneChatItemPreview() {
    OneChatItem(
        model = MessengerChatModel(
            userName = "Iskandar",
            lastMessage = "Last message",
            icon = R.drawable._7163859
        ),
        onChatClick = {}
    )
}
