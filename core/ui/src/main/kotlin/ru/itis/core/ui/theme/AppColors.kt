package ru.itis.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

@Immutable
class AppColors(
    statusBar: Color,
    backgroundPrimary: Color,
    buttonOnPrimary: Color,
    backgroundOnSecondary: Color,
    textHighEmphasis: Color,
    textMediumEmphasis: Color,
    textLowEmphasis: Color,
    errorOnPrimary: Color,
    successOnPrimary: Color,
    bottomBarOnPrimary: Color,
    textFieldOnPrimary: Color,
    checkBoxOnPrimary: Color,
    googleButton: Color,
    googleButtonText: Color,
    isLight: Boolean
) {

    var statusBar by mutableStateOf(statusBar)
        private set
    var backgroundPrimary by mutableStateOf(backgroundPrimary)
        private set
    var backgroundOnSecondary by mutableStateOf(backgroundOnSecondary)
        private set
    var buttonOnPrimary by mutableStateOf(buttonOnPrimary)
        private set
    var textHighEmphasis by mutableStateOf(textHighEmphasis)
        private set
    var textMediumEmphasis by mutableStateOf(textMediumEmphasis)
        private set
    var textLowEmphasis by mutableStateOf(textLowEmphasis)
        private set
    var errorOnPrimary by mutableStateOf(errorOnPrimary)
        private set
    var successOnPrimary by mutableStateOf(successOnPrimary)
        private set
    var bottomBarOnPrimary by mutableStateOf(bottomBarOnPrimary)
        private set
    var textFieldOnPrimary by mutableStateOf(textFieldOnPrimary)
        private set
    var checkBoxOnPrimary by mutableStateOf(checkBoxOnPrimary)
        private set
    var googleButton by mutableStateOf(googleButton)
        private set
    var googleButtonText by mutableStateOf(googleButtonText)
        private set
    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        statusBar: Color = this.statusBar,
        backgroundPrimary: Color = this.backgroundPrimary,
        backgroundOnSecondary: Color = this.backgroundOnSecondary,
        buttonOnPrimary: Color = this.buttonOnPrimary,
        textHighEmphasis: Color = this.textHighEmphasis,
        textMediumEmphasis: Color = this.textMediumEmphasis,
        textLowEmphasis: Color = this.textLowEmphasis,
        errorOnPrimary: Color = this.errorOnPrimary,
        successOnPrimary: Color = this.successOnPrimary,
        bottomBarOnPrimary: Color = this.bottomBarOnPrimary,
        textFieldOnPrimary: Color = this.textFieldOnPrimary,
        checkBoxOnPrimary: Color = this.checkBoxOnPrimary,
        googleButton: Color = this.googleButton,
        googleButtonText: Color = this.googleButtonText,
        isLight: Boolean = this.isLight

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
        isLight = isLight,
    )

    fun updateColorsFrom(colors: AppColors) {
        statusBar = colors.statusBar
        backgroundPrimary = colors.backgroundPrimary
        backgroundOnSecondary = colors.backgroundOnSecondary
        buttonOnPrimary = colors.buttonOnPrimary
        textHighEmphasis = colors.textHighEmphasis
        textMediumEmphasis = colors.textMediumEmphasis
        textLowEmphasis = colors.textLowEmphasis
        errorOnPrimary = colors.errorOnPrimary
        successOnPrimary = colors.successOnPrimary
        bottomBarOnPrimary = colors.bottomBarOnPrimary
        textFieldOnPrimary = colors.textFieldOnPrimary
        googleButton = colors.googleButton
        googleButtonText = colors.googleButtonText
        checkBoxOnPrimary = colors.checkBoxOnPrimary

    }
}

