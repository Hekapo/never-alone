package ru.itis.user_form

/**
 * Copyright (c) 27.05.2022 Created by Iskandar
 */

sealed class UserFormScreens {
    object BirthScreen : UserFormScreens()
    object GenderScreen : UserFormScreens()
    object InterestsScreen : UserFormScreens()
    object AddPhotoScreen : UserFormScreens()
    object None : UserFormScreens()
}
