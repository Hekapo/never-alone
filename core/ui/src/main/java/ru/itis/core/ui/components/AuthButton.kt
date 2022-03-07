package ru.itis.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 07.03.2022.
 */

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    style: TextStyle,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppTheme.colors.buttonOnPrimary
        ),
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            color = color,
            style = style
        )
    }
}

@Preview("Button in Light mode")
@Preview("Button in Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AuthButtonPreview() {
    AuthButton(
        modifier = Modifier.background(AppTheme.colors.backgroundPrimary),
        text = "Auth",
        color = AppTheme.colors.textHighEmphasis,
        style = AppTheme.typography.button
    ) {

    }

}