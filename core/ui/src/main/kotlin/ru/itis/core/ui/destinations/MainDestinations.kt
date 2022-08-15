package ru.itis.core.ui.destinations

import androidx.compose.runtime.Stable

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

@Stable
sealed class MainDestinations(val route: String) {
    object UserFormDestination : MainDestinations("user_form")
    object MainScreenDestination : MainDestinations("main")
    object SettingsScreenDestination : MainDestinations("settings")
}
