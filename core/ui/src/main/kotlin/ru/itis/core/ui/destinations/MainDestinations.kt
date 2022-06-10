package ru.itis.core.ui.destinations

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

sealed class MainDestinations(val route: String) {
    object UserFormDestination : MainDestinations("user_form")
    object MainScreenDestination : MainDestinations("main")
    object SettingsScreenDestination : MainDestinations("settings")
}
