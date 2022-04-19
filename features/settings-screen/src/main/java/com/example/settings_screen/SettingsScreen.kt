package com.example.settings_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Copyright (c) 18.04.2022 Created by Iskandar
 */

@Composable
fun SettingsScreenRoute(deps: SettingsDeps) {

    val settingsComponentViewModel = viewModel<SettingsComponentViewModel>(
        factory = SettingsComponentViewModelFactory(deps)
    )

    val settingsViewModel = viewModel<SettingsViewModel>(
        factory = settingsComponentViewModel.settingsComponent.settingsViewModelFactory
    )

}

@Composable
private fun SettingsScreen() {

}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}
