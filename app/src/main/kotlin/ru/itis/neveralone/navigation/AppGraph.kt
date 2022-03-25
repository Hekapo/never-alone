package ru.itis.neveralone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.itis.core.ui.utils.EmailPassData
import ru.itis.features.loginmethod.LoginMethodRoute
import ru.itis.features.signin.SignInRoute
import ru.itis.features.signup.SignUpRoute
import ru.itis.features.signup.email.create_user.CreateUserRoute
import ru.itis.features.signup.phone.verification.PhoneVerificationRoute
import ru.itis.features.splash.LoadingScreen
import ru.itis.neveralone.di.AppComponent
import ru.itis.neveralone.navigation.Destination.*

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

@Composable
internal fun AppNavGraph(
    navController: NavHostController,
    appComponent: AppComponent
) {
    NavHost(
        navController = navController,
        startDestination = SplashDestination.key
    ) {
        composable(route = SplashDestination.key) {
            LoadingScreen {
                navController.navigate(ChooseLoginMethod.key) {
                    popUpTo(SplashDestination.key) {
                        inclusive = true
                    }
                }
            }

        }
        composable(route = ChooseLoginMethod.key) {
            LoginMethodRoute(
                onSignInScreen = { navController.navigate(route = SignInDestination.key) },
                onSignUpScreen = { navController.navigate(route = SignUpDestination.key) }
            )
        }
        composable(route = SignInDestination.key) {
            SignInRoute(
                signInDeps = appComponent,
                onBackClick = { navController.popBackStack() },
                onTextRegisterClick = { navController.navigate(SignUpDestination.key) }
            )
        }
        composable(route = SignUpDestination.key) {
            SignUpRoute(
                deps = appComponent,
                onNextWithEmailClick = {
                    navController.navigate(setNavigationPath(emailPassData = it))
                },
                onNextWithPhoneClick = { navController.navigate(PhoneVerificationDestination.key) },
                onBackClick = { navController.popBackStack() },
                onTextSignInClick = { navController.navigate(SignInDestination.key) }
            )

        }
        composable(route = PhoneVerificationDestination.key) {
            PhoneVerificationRoute(
                deps = appComponent,
                onNextClick = { /*TODO*/ },
                onBackClick = { /*TODO*/ }) {
            }
        }
        composable(
            route = CreateUserDestination.key.plus("/{${CreateUserDestination.EMAIL}}"),
            arguments = listOf(
                navArgument(
                    CreateUserDestination.EMAIL,
                    builder = { NavType.StringType }
                )
            )
        ) {
            val email = requireNotNull(it.arguments?.getString(CreateUserDestination.EMAIL))

            CreateUserRoute(
                email = email,
                onNextClick = {},
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

private fun setNavigationPath(emailPassData: EmailPassData): String {
    return CreateUserDestination.key.plus("/${emailPassData.email}")
}
