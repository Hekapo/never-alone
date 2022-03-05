package ru.itis.core.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */


class AppColours(
    statusBar: Color,
    backgroundPrimary: Color,
    buttonOnPrimary: Color,
    textOnPrimary: Color,
    textHighEmphasis: Color,
    textMediumEmphasis: Color,
    textLowEmphasis: Color,
    errorOnPrimary: Color,
    successOnPrimary: Color,
    bottomBarOnPrimary: Color,
    textFieldOnPrimary: Color,

    ) {
    var statusBar by mutableStateOf(statusBar)
        private set
    var backgroundPrimary by mutableStateOf(backgroundPrimary)
        private set
    var buttonOnPrimary by mutableStateOf(buttonOnPrimary)
        private set
    var textOnPrimary by mutableStateOf(textOnPrimary)
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
}
