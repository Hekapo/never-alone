package ru.itis.user_form

import androidx.compose.runtime.Stable

/**
 * Copyright (c) 27.05.2022 Created by Iskandar
 */

@Stable
sealed class UserFormScreens(
    val route: String,
) {
    object BirthScreen : UserFormScreens("birth")
    object GenderScreen : UserFormScreens("gender")
    object InterestsScreen : UserFormScreens("interests")
    object AddPhotoScreen : UserFormScreens("add_photo") {
        const val PHOTO_URI = "uri"
    }
    object PickPhotoMethodScreen : UserFormScreens("pick_photo")
}
