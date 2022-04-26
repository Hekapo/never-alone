package ru.itis.main_screen.navigator

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.itis.main_screen.home.HomeScreenRoute
import ru.itis.main_screen.main.MainDeps
import ru.itis.main_screen.main.destinations.MainBottomScreen
import ru.itis.main_screen.messenger.MessengerScreenRoute
import ru.itis.main_screen.profile.ProfileScreenRoute

/**
 * Copyright (c) 26.04.2022 Created by Iskandar
 */


fun NavGraphBuilder.navMain(
    deps: MainDeps,
    navController: NavHostController
) {
    composable(route = MainBottomScreen.Home.route) {
        HomeScreenRoute(deps = deps)
    }

    composable(route = MainBottomScreen.Messenger.route) {
        MessengerScreenRoute(deps = deps)
    }

    composable(route = MainBottomScreen.Profile.route) {
        ProfileScreenRoute(deps = deps)
    }
}
