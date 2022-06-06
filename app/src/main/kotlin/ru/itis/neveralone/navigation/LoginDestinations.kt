package ru.itis.neveralone.navigation

import androidx.compose.runtime.Stable

/**
 * Created by Iskandar on 26.04.2022.
 */

@Stable
internal sealed class LoginDestinations(val route: String) {
    object SplashDestination : LoginDestinations("splash")
    object ChooseLoginMethod : LoginDestinations("login_method")
    object SignInDestination : LoginDestinations("signin")
    object SignUpDestination : LoginDestinations("signup")
    object PhoneVerificationDestination : LoginDestinations("phone_verification")
    object CreateUserDestination : LoginDestinations("create_user") {
        const val EMAIL = "email"
    }
}
