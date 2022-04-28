package ru.itis.main_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 28.04.2022 Created by Iskandar
 */

@Immutable
internal class BottomSheetItem(
    @DrawableRes val icon: Int,
    val title: String
) {
    class Settings : BottomSheetItem()
}

@Composable
internal fun BottomSheetItem(
    icon: Painter,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(color = AppTheme.colors.backgroundPrimary)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = true),
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(44.dp)
                .padding(start = 4.dp),
            painter = icon,
            contentDescription = "",
            tint = AppTheme.colors.textHighEmphasis
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            text = title,
            style = AppTheme.typography.text16M,
            color = AppTheme.colors.textHighEmphasis
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun BottomSheetItemPreview() {
    BottomSheetItem(
        icon = painterResource(id = R.drawable.ic_settings),
        title = "Settings",
        onClick = {}
    )
}
