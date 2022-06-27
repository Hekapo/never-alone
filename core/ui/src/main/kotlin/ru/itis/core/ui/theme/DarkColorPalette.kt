package ru.itis.core.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

fun darkColors(
    statusBar: Color = DarkBackground,
    backgroundPrimary: Color = DarkBackground,
    backgroundOnSecondary: Color = DarkOnSecondary,
    buttonOnPrimary: Color = DarkOnBackgroundBTN,
    textHighEmphasis: Color = DarkTextHighEmphasis,
    textMediumEmphasis: Color = DarkTextMediumEmphasis,
    textLowEmphasis: Color = DarkTextLowEmphasis,
    errorOnPrimary: Color = LightError,
    successOnPrimary: Color = LightSuccess,
    bottomBarOnPrimary: Color = DarkOnBackgroundBNV,
    textFieldOnPrimary: Color = DarkOnBackgroundTF,
    googleButton: Color = DarkOnBackgroundBTN,
    googleButtonText: Color = DarkOnSecondary,
    disabledButton: Color = DarkOnBackgroundBTNDisabled,
    checkBoxOnPrimary: Color = DarkOnBackgroundChB,
    dialogBackground: Color = DarkMaterialDialogBackground,
    dialogPrimary: Color = DarkMaterialDialogPrimary,
    dialogOnSurface: Color = DarkMaterialDialogOnSurface,
    dialogOnSurfaceVariant: Color = DarkMaterialDialogOnSurfaceVariant,
): AppColors = AppColors(
    statusBar = statusBar,
    backgroundPrimary = backgroundPrimary,
    backgroundOnSecondary = backgroundOnSecondary,
    buttonOnPrimary = buttonOnPrimary,
    textHighEmphasis = textHighEmphasis,
    textMediumEmphasis = textMediumEmphasis,
    textLowEmphasis = textLowEmphasis,
    errorOnPrimary = errorOnPrimary,
    successOnPrimary = successOnPrimary,
    bottomBarOnPrimary = bottomBarOnPrimary,
    textFieldOnPrimary = textFieldOnPrimary,
    checkBoxOnPrimary = checkBoxOnPrimary,
    googleButton = googleButton,
    googleButtonText = googleButtonText,
    disabledButton = disabledButton,
    dialogBackground = dialogBackground,
    dialogPrimary = dialogPrimary,
    dialogOnSurface = dialogOnSurface,
    dialogOnSurfaceVariant = dialogOnSurfaceVariant,
    isLight = true
)
