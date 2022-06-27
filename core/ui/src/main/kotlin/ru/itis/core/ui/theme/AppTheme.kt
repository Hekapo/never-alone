package ru.itis.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

@Composable
fun AppTheme(
    colors: AppColors = AppTheme.colors,
    typography: AppTypography = AppTheme.typography,
    content: @Composable () -> Unit
) {

    val systemDark = isSystemInDarkTheme()
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }

    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colors.statusBar,
            darkIcons = !systemDark,
        )
    }

    MaterialTheme(
        typography = Typography()
    ) {
        CompositionLocalProvider(
            LocalAppColors provides rememberedColors,
            LocalAppDarkColors provides rememberedColors,
            LocalAppTypography provides typography,
        ) {
            content()
        }
    }

}

@Immutable
object AppTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = if (isSystemInDarkTheme()) LocalAppDarkColors.current else LocalAppColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

}

internal val LocalAppColors = staticCompositionLocalOf {
    lightColors()
}
internal val LocalAppDarkColors = staticCompositionLocalOf {
    darkColors()
}
