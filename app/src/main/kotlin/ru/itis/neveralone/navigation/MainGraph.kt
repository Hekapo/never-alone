package ru.itis.neveralone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.settings_screen.SettingsScreenRoute
import ru.itis.main_screen.main.MainScreenRoute
import ru.itis.neveralone.di.AppComponent
import ru.itis.neveralone.navigation.MainDestinations.*
import ru.itis.onboarding.OnBoardingRoute
import ru.itis.user_form.UserFormRoute

/**
 * Copyright (c) 11.04.2022 Created by Iskandar
 */

@Composable
internal fun MainNavGraph(
    navController: NavHostController,
    appComponent: AppComponent,
    showOnBoarding: Boolean?,
) {
    NavHost(
        navController = navController,
        startDestination = if (showOnBoarding == true) OnBoardingScreenDestination.route else MainScreenDestination.route
    ) {
        composable(route = OnBoardingScreenDestination.route) {
            OnBoardingRoute(
                deps = appComponent,
                onFinishClick = {
                    navController.navigate(route = UserFormDestination.route) {
                        popUpTo(OnBoardingScreenDestination.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = UserFormDestination.route) {
            UserFormRoute(deps = appComponent) {
                navController.navigate(route = MainScreenDestination.route) {
                    popUpTo(UserFormDestination.route) {
                        inclusive = true
                    }
                }
            }
        }

        composable(route = MainScreenDestination.route) {
            MainScreenRoute(deps = appComponent)
        }

        composable(route = SettingsScreenDestination.route) {
            SettingsScreenRoute(deps = appComponent)
        }
    }
}
