package ru.itis.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme {
//        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
//        typography = JetnewsTypography,
//        shapes = JetnewsShapes,
//        content = content
//        )
    }
}

//private val LightThemeColors = lightColors(
//    primary = Red700,
//    primaryVariant = Red900,
//    onPrimary = Color.White,
//    secondary = Red700,
//    secondaryVariant = Red900,
//    onSecondary = Color.White,
//    error = Red800,
//    onBackground = Color.Black,
//
//)
//
//private val DarkThemeColors = darkColors(
//    primary = Red300,
//    primaryVariant = Red700,
//    onPrimary = Color.Black,
//    secondary = Red300,
//    onSecondary = Color.Black,
//    error = Red200,
//    onBackground = Color.White
//)
//
//@Composable
//fun JetnewsTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    content: @Composable () -> Unit
//) {
//    MaterialTheme(
//        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
//        typography = JetnewsTypography,
//        shapes = JetnewsShapes,
//        content = content
//    )
//}