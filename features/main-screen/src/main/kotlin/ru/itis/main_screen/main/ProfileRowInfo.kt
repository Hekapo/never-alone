package ru.itis.main_screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 04.04.2022.
 */

@Composable
fun ProfileRowInfo(
    title: String,
    titleColor: Color = AppTheme.colors.textMediumEmphasis,
    titleTextStyle: TextStyle = AppTheme.typography.text16M,
    mainText: String,
    mainTextColor: Color = AppTheme.colors.textHighEmphasis,
    mainTextStyle: TextStyle = AppTheme.typography.text20M,
    needRow: Boolean,
    rowColor: Color = AppTheme.colors.textLowEmphasis
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.backgroundPrimary),
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp, top = 8.dp),
            text = title,
            color = titleColor,
            style = titleTextStyle
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = mainText,
            color = mainTextColor,
            style = mainTextStyle
        )

    }

}

@Preview
@Composable
fun ProfileRowInfoPreview() {

    ProfileRowInfo(title = "Full name", mainText = "Iskandar", needRow = true)
}