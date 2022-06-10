package ru.itis.core.ui.destinations

import androidx.compose.runtime.Stable

/**
 * Created by Iskandar on 26.04.2022.
 */

const val LOGIN_GRAPH_ROUTE = "login_graph"
const val ONBOARDING_GRAPH_ROUTE = "on_boarding_graph"

@Stable
sealed class LoginDestinations(val route: String) {
    object SplashDestination : LoginDestinations("splash")
    object OnBoardingScreenDestination : LoginDestinations("on_boarding")
    object ChooseLoginMethod : LoginDestinations("login_method")
    object SignInDestination : LoginDestinations("signin")
    object SignUpDestination : LoginDestinations("signup")
    object PhoneVerificationDestination : LoginDestinations("phone_verification")
    object CreateUserDestination : LoginDestinations("create_user") {
        const val EMAIL = "email"
    }
}
