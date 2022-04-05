package ru.itis.main_screen.messenger.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 04.04.2022.
 */

@Composable
internal fun MessengerViewNoChats() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.backgroundPrimary)
            .height(150.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "No chats")
    }
}

@Preview
@Composable
private fun MessengerViewNoChatsPreview() = MessengerViewNoChats()
