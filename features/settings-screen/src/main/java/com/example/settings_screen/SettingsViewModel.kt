package com.example.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by Iskandar on 20.04.2022.
 */

internal class SettingsViewModel() : ViewModel() {

    private val _searchValue = MutableStateFlow(SettingsUIState())
    val searchValue = _searchValue.asStateFlow()

    fun searchValueChanged(value: String) {
        _searchValue.update {
            it.copy(searchFieldText = value)
        }
    }

    internal class SettingsViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel() as T
        }
    }
}
