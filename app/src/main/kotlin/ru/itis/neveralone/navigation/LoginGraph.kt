package ru.itis.neveralone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.itis.core.common.buildPath
import ru.itis.core.ui.destinations.LoginDestinations.*
import ru.itis.core.ui.destinations.MainDestinations
import ru.itis.core.ui.utils.EmailPassData
import ru.itis.features.signin.SignInRoute
import ru.itis.features.signup.SignUpRoute
import ru.itis.features.signup.email.create_user.CreateUserRoute
import ru.itis.features.signup.login_method.LoginMethodRoute
import ru.itis.features.signup.phone.verification.PhoneVerificationRoute
import ru.itis.features.splash.LoadingScreen
import ru.itis.neveralone.di.AppComponent
import ru.itis.onboarding.navigation.onBoardingNavGraph

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

@Composable
internal fun LoginNavGraph(
    navController: NavHostController,
    appComponent: AppComponent,
    toMainScreen: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = SplashDestination.route
    ) {
        composable(route = SplashDestination.route) {
            LoadingScreen(
                deps = appComponent,
                toMainScreen = { toMainScreen() },
                toRegistration = {
                    navController.navigate(MainDestinations.MainScreenDestination.route) {
                        popUpTo(SplashDestination.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        onBoardingNavGraph(
            deps = appComponent,
            navController = navController
        )

        composable(route = ChooseLoginMethod.route) {
            LoginMethodRoute(
                deps = appComponent,
                onSignInScreen = { navController.navigate(route = SignInDestination.route) },
                onSignUpScreen = { navController.navigate(route = SignUpDestination.route) }
            )
        }
        composable(route = SignInDestination.route) {
            SignInRoute(
                signInDeps = appComponent,
                onBackClick = { navController.popBackStack() },
                onTextRegisterClick = {
                    if (!navController.popBackStack(route = SignUpDestination.route, false)) {
                        navController.navigate(route = SignUpDestination.route)
                    }
                },
                onNext = { toMainScreen() }
            )
        }
        composable(route = SignUpDestination.route) {
            SignUpRoute(
                deps = appComponent,
                onNextWithEmailClick = {
                    navController.navigate(CreateUserDestination.route buildPath it.email)
                },
                onNextWithPhoneClick = { navController.navigate(PhoneVerificationDestination.route) },
                onBackClick = { navController.popBackStack() },
                onTextSignInClick = {
                    if (!navController.popBackStack(route = SignInDestination.route, false)) {
                        navController.navigate(route = SignInDestination.route)
                    }
                }
            )
        }
        composable(route = PhoneVerificationDestination.route) {
            PhoneVerificationRoute(
                deps = appComponent,
                onNextClick = { /*TODO*/ },
                onBackClick = { /*TODO*/ }) {
            }
        }
        composable(
            route = CreateUserDestination.route.plus("/{${CreateUserDestination.EMAIL}}"),
            arguments = listOf(
                navArgument(
                    CreateUserDestination.EMAIL,
                    builder = { NavType.StringType }
                )
            )
        ) {
            val email = requireNotNull(it.arguments?.getString(CreateUserDestination.EMAIL))

            CreateUserRoute(
                email = EmailPassData(email),
                deps = appComponent,
                onNextClick = { toMainScreen() },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
