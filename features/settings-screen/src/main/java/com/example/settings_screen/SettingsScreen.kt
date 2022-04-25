@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.settings_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.core.ui.R
import ru.itis.core.ui.components.BasicTextField
import ru.itis.core.ui.components.ImageTopAppBar
import ru.itis.core.ui.components.SearchField
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

    val uiState by settingsViewModel.searchValue.collectAsState()

    SettingsScreen(
        uiState = uiState,
        searchValueChanged = settingsViewModel::searchValueChanged,
        onSearchFieldClick = {},
        onClick = {}
    )
}

@Composable
private fun SettingsScreen(
    uiState: SettingsUIState,
    searchValueChanged: (String) -> Unit,
    onSearchFieldClick: () -> Unit,
    onClick: () -> Unit
) {

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
                text = stringResource(id = R.string.title_settings),
                style = AppTheme.typography.text28R,
                color = AppTheme.colors.textHighEmphasis
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            SettingsTopSearchField(
                inputText = uiState.searchFieldText,
                onTextChanged = searchValueChanged,
                onClick = onSearchFieldClick
            )
            LazyColumn(
                modifier = Modifier.padding(top = 56.dp),
            ) {
                Constants.settingsList.forEach { (key, value) ->
                    item {
                        SettingsListItem(title = value) {
                            Log.e("DEBUG", key.toString())
                            onClick()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsTopSearchField(
    inputText: String,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    onClick: () -> Unit,
    onTextChanged: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    SearchField(onClick = onClick) {
        Row(
            modifier = Modifier.padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_18),
                    contentDescription = "",
                    tint = AppTheme.colors.textLowEmphasis
                )
            }
            BasicTextField(
                inputValue = inputText,
                placeholder = stringResource(id = R.string.search_settings),
                onValueChange = onTextChanged,
                focusRequester = focusRequester
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(uiState = SettingsUIState(), onClick = {}, searchValueChanged = {}, onSearchFieldClick = {})
}
