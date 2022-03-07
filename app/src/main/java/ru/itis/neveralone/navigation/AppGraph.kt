package ru.itis.neveralone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.itis.features.loginmethod.LoginMethodScreen
import ru.itis.features.splash.LoadingScreen

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

@Composable
internal fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Destination.SplashDestination.key
    ) {
        composable(route = Destination.SplashDestination.key) {
            LoadingScreen {
                navController.navigate(Destination.ChooseLoginMethod.key)
            }

        }
        composable(route = Destination.ChooseLoginMethod.key) {
            LoginMethodScreen(
                onSignInScreen = { navController.navigate(route = Destination.SignInDestination.key) },
                onSignUpScreen = { navController.navigate(route = Destination.SignUpDestination.key) }
            )
        }
    }

}
