package ru.itis.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 21.03.2022.
 */

@Composable
fun Snackbar(
    modifier: Modifier = Modifier,
    message: String,
    isError: Boolean = false,
    visible: Boolean = false
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideIn(
            initialOffset = { fullSize ->
                IntOffset(
                    x = 0,
                    y = fullSize.height
                )
            }
        ),
        exit = slideOut(targetOffset = { fullSize ->
            IntOffset(
                0,
                +fullSize.height
            )
        })
    ) {
        Snackbar(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(3.dp),
            backgroundColor = if (!isError) AppTheme.colors.successOnPrimary else AppTheme.colors.errorOnPrimary,
        ) {
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = message,
                style = AppTheme.typography.text14M,
                color = Color.White,
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SnackbarPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.backgroundPrimary),
        contentAlignment = Alignment.BottomCenter
    ) {
        Snackbar(
            modifier = Modifier.padding(16.dp),
            message = "Message",
            isError = true,
            visible = true
        )
    }
}
