package com.example.settings_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.core.ui.R
import ru.itis.core.ui.components.ImageTopAppBar
import ru.itis.core.ui.theme.AppTheme

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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.backgroundPrimary)
    ) {
        ImageTopAppBar(
            centerImageVector = R.drawable.leaves,
            backArrowClick = {}
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Settings",
                style = AppTheme.typography.text28R,
                color = AppTheme.colors.textHighEmphasis
            )
        }
        Box(modifier = Modifier.weight(1f)) {

        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}
