package ru.itis.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.itis.core.ui.destinations.LoginDestinations
import ru.itis.core.ui.destinations.ONBOARDING_GRAPH_ROUTE
import ru.itis.onboarding.OnBoardingDeps
import ru.itis.onboarding.OnBoardingRoute

/**
 * Copyright (c) 10.06.2022 Created by Iskandar
 */

fun NavGraphBuilder.onBoardingNavGraph(
    deps: OnBoardingDeps,
    navController: NavController
) {
    navigation(
        startDestination = LoginDestinations.OnBoardingScreenDestination.route,
        route = ONBOARDING_GRAPH_ROUTE
    ) {
        composable(
            route = LoginDestinations.OnBoardingScreenDestination.route
        ) {
            OnBoardingRoute(
                deps = deps,
                onFinishClick = {
                    navController.navigate(route = LoginDestinations.ChooseLoginMethod.route) {
                        popUpTo(LoginDestinations.OnBoardingScreenDestination.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
