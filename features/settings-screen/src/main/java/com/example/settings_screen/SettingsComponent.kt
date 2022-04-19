package com.example.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component

/**
 * Created by Iskandar on 20.04.2022.
 */

@Component(dependencies = [SettingsDeps::class])
internal interface SettingsComponent {

    val settingsViewModelFactory: SettingsViewModel.SettingsViewModelFactory

    @Component.Builder
    interface Builder {

        fun deps(deps: SettingsDeps): Builder

        val build: SettingsComponent
    }
}

interface SettingsDeps {

}

internal class SettingsComponentViewModel(deps: SettingsDeps) : ViewModel() {
    val settingsComponent = DaggerSettingsComponent.builder().deps(deps).build
}

internal class SettingsComponentViewModelFactory(
    private val deps: SettingsDeps
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsComponentViewModel(deps) as T
    }
}
