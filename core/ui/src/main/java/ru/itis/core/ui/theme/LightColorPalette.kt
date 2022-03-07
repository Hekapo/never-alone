package ru.itis.core.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

fun lightColors(
    statusBar: Color = LightBackground,
    backgroundPrimary: Color = LightBackground,
    backgroundOnSecondary: Color = LightOnSecondary,
    buttonOnPrimary: Color = LightOnBackgroundBTN,
    textHighEmphasis: Color = LightTextHighEmphasis,
    textMediumEmphasis: Color = LightTextMediumEmphasis,
    textLowEmphasis: Color = LightTextLowEmphasis,
    errorOnPrimary: Color = LightError,
    successOnPrimary: Color = LightSuccess,
    bottomBarOnPrimary: Color = LightOnBackgroundBNV,
    textFieldOnPrimary: Color = LightOnBackgroundTF,
    checkBoxOnPrimary: Color = LightOnBackgroundChB,
): AppColors = AppColors(
    statusBar = statusBar,
    backgroundPrimary = backgroundPrimary,
    backgroundOnPrimary = buttonOnPrimary,
    backgroundOnSecondary = backgroundOnSecondary,
    textHighEmphasis = textHighEmphasis,
    textMediumEmphasis = textMediumEmphasis,
    textLowEmphasis = textLowEmphasis,
    errorOnPrimary = errorOnPrimary,
    successOnPrimary = successOnPrimary,
    bottomBarOnPrimary = bottomBarOnPrimary,
    textFieldOnPrimary = textFieldOnPrimary,
    checkBoxOnPrimary = checkBoxOnPrimary,
    isLight = true
)
