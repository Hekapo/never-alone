@file:OptIn(ExperimentalComposeUiApi::class)

package ru.itis.features.signin

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import ru.itis.core.ui.R
import ru.itis.core.ui.common.FieldCorrectnessCheck
import ru.itis.core.ui.components.*
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 07.03.2022.
 */

@Composable
fun SignInRoute(
    signInDeps: SignInDeps,
    onBackClick: () -> Unit,
    onTextRegisterClick: () -> Unit
) {

    val signInComponentViewModel = viewModel<SignInComponentViewModel>(
        factory = SignInComponentViewModelFactory(signInDeps)
    )

    val signInViewModel = viewModel<SignInViewModel>(
        factory = signInComponentViewModel.signInComponent.getViewModelFactory()
    )

    val uiState by signInViewModel.signInUIState.collectAsState()

//    LaunchedEffect(uiState.signInProcess.signInSuccess) {
//        if (uiState.signInProcess.signInSuccess) {
//            delay(300)
//        }
//    }

    SignInScreen(
        uiState = uiState,
        onEmailChanged = signInViewModel::onEmailChanged,
        onPasswordChanged = signInViewModel::onPasswordChanged,
        onEnterClick = signInViewModel::onSignInClick,
        onBackClick = onBackClick,
        onTextRegisterClick = onTextRegisterClick
    )

}

@Composable
private fun SignInScreen(
    uiState: SignInUIState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onEnterClick: () -> Unit,
    onBackClick: () -> Unit,
    onTextRegisterClick: () -> Unit
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary)
    ) {
        Column {
            NoInternetWarn(internetAvailable = uiState.internetAvailable)
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
        }

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
                style = AppTheme.typography.text36R,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.textHighEmphasis
            )
            Spacer(modifier = Modifier.height(48.dp))
            AppTextField(
                text = uiState.inputEmail.email,
                isEnabled = uiState.inputEmail.isFieldEnabled,
                placeholder = stringResource(id = R.string.enter_email_hint),
                onChange = onEmailChanged,
                isError = uiState.inputEmail.showError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                keyboardActions = KeyboardActions { keyboardController?.hide() }
            )
            Spacer(modifier = Modifier.height(16.dp))
            AppTextField(
                text = uiState.inputPassword.password,
                isEnabled = uiState.inputPassword.isFieldEnabled,
                placeholder = stringResource(id = R.string.enter_password_hint),
                onChange = onPasswordChanged,
                visualTransformation = if (isPasswordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                isError = uiState.inputPassword.showError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions { keyboardController?.hide() },
                leadingIcon = {
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }) {
                        Icon(
                            imageVector = if (isPasswordVisible)
                                Icons.Filled.Visibility
                            else
                                Icons.Filled.VisibilityOff,
                            contentDescription = "Password Visibility",
                            tint = AppTheme.colors.textMediumEmphasis
                        )
                    }
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthButton(
                text = stringResource(id = R.string.enter),
                style = AppTheme.typography.text14M,
                isLoading = uiState.isLoading,
                enabled = !uiState.isLoading &&
                        uiState.inputPassword.showError is FieldCorrectnessCheck.Success &&
                        uiState.inputEmail.showError is FieldCorrectnessCheck.Success &&
                        uiState.internetAvailable,
                onClick = onEnterClick
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    color = AppTheme.colors.textLowEmphasis,
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f, fill = false)
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = stringResource(id = R.string.or),
                    color = AppTheme.colors.textLowEmphasis,
                    style = AppTheme.typography.text12R
                )
                Divider(
                    color = AppTheme.colors.textLowEmphasis,
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f, fill = false)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            GoogleButton(text = stringResource(id = R.string.signin_with_google)) {

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
                    text = stringResource(id = R.string.no_account),
                    color = AppTheme.colors.textMediumEmphasis,
                    style = AppTheme.typography.text14M
                )
                Text(
                    modifier = Modifier.clickable(
                        role = Role.Button,
                        onClick = onTextRegisterClick
                    ),
                    text = stringResource(id = R.string.register),
                    color = AppTheme.colors.textHighEmphasis,
                    style = AppTheme.typography.text14M
                )

            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Snackbar(
                message = uiState.snackBar.message,
                isError = uiState.snackBar.isError,
                visible = uiState.snackBar.show
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignInPreview() {
    SignInScreen(
        uiState = SignInUIState(),
        onEmailChanged = {},
        onPasswordChanged = {},
        onEnterClick = {},
        onBackClick = {},
        onTextRegisterClick = {}
    )
}
