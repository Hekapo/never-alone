package ru.itis.neveralone.navigation

/**
 * Copyright (c) 05.03.2022 Created by Iskandar
 */

internal sealed class Destination(val key: String) {
    object SplashDestination : Destination("splash")
    object ChooseLoginMethod : Destination("login_method")
    object SignInDestination : Destination("signin")
    object SignUpDestination : Destination("signup")
}
