package ru.itis.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 07.03.2022.
 */

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = AppTheme.colors.backgroundOnSecondary,
    style: TextStyle = AppTheme.typography.text14M,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppTheme.colors.buttonOnPrimary,
            disabledBackgroundColor = AppTheme.colors.disabledButton,
        ),
        enabled = enabled,
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            color = color,
            style = style
        )
        if (isLoading) {
            Spacer(modifier = Modifier.width(16.dp))
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = AppTheme.colors.backgroundOnSecondary,
                strokeWidth = 2.dp
            )
        }
    }
}

@Preview("Button in Light mode")
@Preview("Button in Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AuthButtonPreview() {
    AuthButton(
        modifier = Modifier.background(AppTheme.colors.backgroundPrimary),
        text = "Auth",
        enabled = true,
        isLoading = true
    ) {

    }

}
