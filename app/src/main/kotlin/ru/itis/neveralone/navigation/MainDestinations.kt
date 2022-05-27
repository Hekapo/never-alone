package ru.itis.neveralone.navigation

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

internal sealed class MainDestinations(val route: String) {
    object OnBoardingScreenDestination : MainDestinations("onBoarding")
    object UserFormDestination : MainDestinations("user_form")
    object MainScreenDestination : MainDestinations("main")
    object SettingsScreenDestination : MainDestinations("settings")
}
