@file:OptIn(ExperimentalComposeUiApi::class)

package ru.itis.features.signup

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.delay
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.components.LoginTextField
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */

@Composable
fun SignUpRoute(
    signUpDeps: SignUpDeps,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    onTextSignInClick: () -> Unit
) {

    val signUpComponentViewModel = viewModel<SignUpComponentViewModel>(
        factory = SignUpComponentViewModelFactory(signUpDeps)
    )

    val signUpViewModel = viewModel<SignUpViewModel>(
        factory = signUpComponentViewModel.signUpComponent.getViewModelFactory()
    )

    val uiState by signUpViewModel.signUpUIState.collectAsState()

    LaunchedEffect(uiState.signUpProcess.signUpSuccess) {
        if (uiState.signUpProcess.signUpSuccess) {
            delay(300)
            onBackClick()
        }
    }

    SignUpScreen(
        uiState = uiState,
        onTextSignInClick = onTextSignInClick,
        onNextClick = onNextClick,
        onBackClick = onBackClick,
        onEmailChange = signUpViewModel::onEmailChange
    )
}

@Composable
fun SignUpScreen(
    uiState: SignUpUIState,
    onTextSignInClick: () -> Unit,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    onEmailChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary)
    ) {
        IconButton(
            onClick = {
                keyboardController?.hide()
                onBackClick()
            },
            content = {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = AppTheme.colors.textHighEmphasis
                )
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.app_name),
                style = AppTheme.typography.title1,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.textHighEmphasis
            )
            Spacer(modifier = Modifier.height(48.dp))
            LoginTextField(
                inputValue = uiState.inputEmail.email,
                onValueChange = onEmailChange,
                isEnabled = uiState.inputEmail.isFieldEnabled,
                placeholder = stringResource(id = R.string.enter_email_hint),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                keyboardActions = KeyboardActions { keyboardController?.hide() }
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthButton(
                text = stringResource(id = R.string.next),
                color = AppTheme.colors.backgroundOnSecondary,
                style = AppTheme.typography.button
            ) {
                onNextClick()
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Row {
                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = stringResource(id = R.string.have_an_account),
                    color = AppTheme.colors.textMediumEmphasis,
                    style = AppTheme.typography.textField
                )
                Text(
                    modifier = Modifier.clickable(
                        role = Role.Button,
                        onClick = onTextSignInClick
                    ),
                    text = stringResource(id = R.string.signin),
                    color = AppTheme.colors.textHighEmphasis,
                    style = AppTheme.typography.textField
                )

            }
        }

    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignUpPreview() {
    SignUpScreen(
        uiState = SignUpUIState(),
        onTextSignInClick = {},
        onNextClick = {},
        onBackClick = {},
        onEmailChange = {}
    )

}
