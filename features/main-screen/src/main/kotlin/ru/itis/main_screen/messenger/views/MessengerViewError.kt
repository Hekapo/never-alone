package ru.itis.main_screen.messenger.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Iskandar on 06.04.2022.
 */

@Composable
fun MessengerViewError() {
    Column {
        Spacer(modifier = Modifier.height(24.dp))

    }

}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MessengerViewErrorPreview() {
    MessengerViewError()

}
