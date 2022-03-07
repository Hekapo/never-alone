package ru.itis.core.ui.theme

import androidx.compose.runtime.*

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

@Composable
fun AppTheme(
    colors: AppColors = AppTheme.colors,
    typography: AppTypography = AppTheme.typography,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }

    CompositionLocalProvider(
        LocalAppColors provides rememberedColors,
        LocalAppTypography provides typography,
    ) {
        content()
    }
}

object AppTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

}

internal val LocalAppColors = staticCompositionLocalOf {
    lightColors()
}
