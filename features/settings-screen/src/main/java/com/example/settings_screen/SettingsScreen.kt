@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.settings_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.core.ui.R
import ru.itis.core.ui.components.ImageTopAppBar
import ru.itis.core.ui.components.LoginTextField
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
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            SettingsTopSearchField(
                inputText = "",
                onTextChanged = {}
            )
        }
    }
}

@Composable
private fun SettingsTopSearchField(
    inputText: String,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    onTextChanged: (String) -> Unit
) {
    SearchField(onClick = { /*TODO*/ }) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = { keyboardController?.hide() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_18),
                    contentDescription = "",
                    tint = AppTheme.colors.textLowEmphasis
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            LoginTextField(
                inputValue = inputText,
                placeholder = stringResource(id = R.string.search_settings),
                onValueChange = onTextChanged,
                keyboardActions = KeyboardActions { keyboardController?.hide() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                trailingIcon = {
//                    AnimatedVisibility(
//                        visible = showCloseIcon,
//                        enter = scaleIn(),
//                        exit = scaleOut()
//                    ) {
//                        ClickableIcon(
//                            backgroundColor = AppTheme.colors.backgroundPrimary,
//                            imageVector = Icons.Default.Close,
//                            iconTint = AppTheme.colors.buttonPrimary,
//                            contentDescription = stringResource(id = androidx.compose.foundation.layout.R.string.descriptionIconClose),
//                            onClick = { onTextChanged("") }
//                        )
//                    }
//                }
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}
