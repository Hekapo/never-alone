package ru.itis.neveralone.navigation

/**
 * Created by Iskandar on 26.04.2022.
 */

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
