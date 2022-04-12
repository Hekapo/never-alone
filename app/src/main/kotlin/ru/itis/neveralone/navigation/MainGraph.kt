package ru.itis.neveralone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.itis.main_screen.main.MainScreenRoute
import ru.itis.neveralone.di.AppComponent

/**
 * Copyright (c) 11.04.2022 Created by Iskandar
 */

@Composable
internal fun MainNavGraph(
    navController: NavHostController,
    appComponent: AppComponent,
) {
    NavHost(
        navController = navController,
        startDestination = Destination.MainScreenDestination.key
    ) {
        composable(route = Destination.MainScreenDestination.key) {
            MainScreenRoute(deps = appComponent)
        }
    }
}
