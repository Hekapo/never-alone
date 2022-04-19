package com.example.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Iskandar on 20.04.2022.
 */

internal class SettingsViewModel() : ViewModel() {


    internal class SettingsViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel() as T
        }
    }
}
