package ru.itis.features.signup.email.create_user

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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.delay
import ru.itis.core.ui.R
import ru.itis.core.ui.common.FieldCorrectnessCheck
import ru.itis.core.ui.components.AppTextField
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.components.NoInternetWarn
import ru.itis.core.ui.components.Snackbar
import ru.itis.core.ui.firebase_exceptions.authCodeMap
import ru.itis.core.ui.theme.AppTheme
import ru.itis.core.ui.utils.EmailPassData

/**
 * Created by Iskandar on 25.03.2022.
 */

@Composable
fun CreateUserRoute(
    email: EmailPassData,
    deps: CreateUserDeps,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
) {

    val createUserComponentViewModel = viewModel<CreateUserComponentViewModel>(
        factory = CreateUserComponentViewModelFactory(deps, email)
    )

    val createUserViewModel = viewModel<CreateUserViewModel>(
        factory = createUserComponentViewModel.createUserComponent.getViewModelFactory()
    )

    val uiState by createUserViewModel.emailUIState.collectAsState()

    LaunchedEffect(key1 = uiState.couldNavigate) {
        if (uiState.couldNavigate) {
            onNextClick()
        }
    }

    LaunchedEffect(key1 = uiState.snackBar.show) {
        delay(1500L)
        if (uiState.snackBar.show) {
            createUserViewModel.hideSnackbar()
        }
    }

    CreateUserScreen(
        uiState = uiState,
        onNameChange = createUserViewModel::onNameChange,
        onPasswordChange = createUserViewModel::onPasswordChange,
        onNextClick = {
            createUserViewModel.createUser()
        },
        onBackClick = onBackClick
    )

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CreateUserScreen(
    uiState: CreateUserUIState,
    onNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    var isPasswordVisible by remember { mutableStateOf(false) }

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
            AppTextField(
                text = uiState.inputUserName.name,
                onChange = onNameChange,
                isEnabled = uiState.inputUserName.isFieldEnabled,
                placeholder = stringResource(id = R.string.user_name),
                isError = uiState.inputUserName.showError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                keyboardActions = KeyboardActions { keyboardController?.hide() }
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 4.dp),
                text = stringResource(id = R.string.cant_change_name),
                textAlign = TextAlign.Start,
                color = AppTheme.colors.textMediumEmphasis,
                style = AppTheme.typography.text14R
            )
            Spacer(modifier = Modifier.height(16.dp))
            AppTextField(
                text = uiState.inputPassword.password,
                onChange = onPasswordChange,
                isError = uiState.inputPassword.showError,
                visualTransformation = if (isPasswordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                isEnabled = uiState.inputPassword.isFieldEnabled,
                placeholder = stringResource(id = R.string.enter_password_hint),
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
                text = stringResource(id = R.string.continue_text),
                style = AppTheme.typography.text14M,
                enabled = uiState.inputPassword.showError is FieldCorrectnessCheck.Success && uiState.internetAvailable && !uiState.isLoading,
                isLoading = uiState.isLoading,
                onClick = onNextClick
            )

        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Snackbar(
                message = stringResource(id = uiState.snackBar.message.authCodeMap()),
                isError = uiState.snackBar.isError,
                visible = uiState.snackBar.show
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun CreateUserScreenPreview() {
    CreateUserScreen(
        CreateUserUIState(),
        onNameChange = {},
        onPasswordChange = {},
        onNextClick = {},
        onBackClick = {}
    )
}
