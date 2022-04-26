package ru.itis.neveralone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.settings_screen.SettingsScreenRoute
import ru.itis.main_screen.main.MainScreenRoute
import ru.itis.neveralone.di.AppComponent
import ru.itis.neveralone.navigation.MainDestinations.*

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
        startDestination = MainScreenDestination.route
    ) {
        composable(route = MainScreenDestination.route) {
            MainScreenRoute(deps = appComponent)
        }

        composable(route = SettingsScreenDestination.route){
            SettingsScreenRoute(deps = appComponent)
        }
    }
}
