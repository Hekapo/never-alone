package ru.itis.main_screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Copyright (c) 04.04.2022 Created by Iskandar
 */

internal class ProfileViewModel() : ViewModel() {

     class ProfileViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProfileViewModel() as T
        }
    }
}
