@file:OptIn(ExperimentalComposeUiApi::class)

package ru.itis.features.signin

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
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
 * Created by Iskandar on 07.03.2022.
 */

@Composable
fun SignInRoute(
    signInDeps: SignInDeps,
    onBackClick: () -> Unit
) {

    val signInComponentViewModel = viewModel<SignInComponentViewModel>(
        factory = SignInComponentViewModelFactory(signInDeps)
    )

    val signInViewModel = viewModel<SignInViewModel>(
        factory = signInComponentViewModel.signInComponent.getViewModelFactory()
    )

    val uiState by signInViewModel.signInUIState.collectAsState()

    LaunchedEffect(uiState.signInProcess.signInSuccess) {
        if (uiState.signInProcess.signInSuccess) {
            delay(300)
            onBackClick()
        }
    }

    SignInScreen(
        uiState = uiState,
        onEmailChanged = signInViewModel::onEmailChanged,
        onPasswordChanged = signInViewModel::onPasswordChanged,
        onBackClick = onBackClick
    )

}

@Composable
private fun SignInScreen(
    uiState: SignInUIState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onBackClick: () -> Unit
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
            UserEmailInput(
                inputValue = uiState.inputEmail.email,
                inputEnabled = uiState.inputEmail.isFieldEnabled,
                onValueChange = onEmailChanged
            )
            Spacer(modifier = Modifier.height(16.dp))
            UserPasswordInput(
                inputValue = uiState.inputPassword.password,
                inputEnabled = uiState.inputPassword.isFieldEnabled,
                onValueChange = onPasswordChanged
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthButton(
                text = stringResource(id = R.string.enter),
                color = AppTheme.colors.backgroundOnSecondary,
                style = AppTheme.typography.button
            ) {

            }
        }
    }


}

@Composable
private fun UserEmailInput(
    inputValue: String,
    inputEnabled: Boolean,
    keyboardController: SoftwareKeyboardController? = null,
    onValueChange: (String) -> Unit,
) {
    LoginTextField(
        inputValue = inputValue,
        isEnabled = inputEnabled,
        placeholder = stringResource(id = R.string.enter_email_hint),
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        keyboardActions = KeyboardActions { keyboardController?.hide() }
    )
}

@Composable
private fun UserPasswordInput(
    inputValue: String,
    inputEnabled: Boolean,
    keyboardController: SoftwareKeyboardController? = null,
    onValueChange: (String) -> Unit,
) {
    LoginTextField(
        inputValue = inputValue,
        isEnabled = inputEnabled,
        placeholder = stringResource(id = R.string.enter_password_hint),
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        keyboardActions = KeyboardActions { keyboardController?.hide() }
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignInPreview() {
    SignInScreen(
        uiState = SignInUIState(),
        onEmailChanged = {},
        onPasswordChanged = {},
        onBackClick = {}
    )
}
