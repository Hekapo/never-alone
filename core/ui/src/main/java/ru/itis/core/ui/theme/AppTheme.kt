package ru.itis.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.itis.core.ui.theme.AppTheme.typography

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colours = if (darkTheme) DarkColorPalette else LightColorPalette
    val colorPalette = remember { colours }
    colorPalette.update(colours)

    val selectionColours = remember {
        TextSelectionColors(
            handleColor = colours.textMediumEmphasis,
            backgroundColor = colours.textLowEmphasis
        )
    }

    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colours.statusBar,
            darkIcons = !darkTheme
        )
    }

    MaterialTheme(
        typography = Typography(),
    ) {
        CompositionLocalProvider(
            LocalAppColours provides colorPalette,
            LocalAppTypography provides typography,
            LocalTextSelectionColors provides selectionColours,
            content = content,
        )
    }
}

object AppTheme {
    val colors: AppColours
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColours.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

}

private val LocalAppColours = staticCompositionLocalOf<AppColours> {
    error("No LocalAppColors provided")
}
