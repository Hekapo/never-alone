package ru.itis.features.signup.email.create_user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.components.LoginTextField
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

    CreateUserScreen(
        uiState = uiState,
        onNameChange = createUserViewModel::onNameChange,
        onPasswordChange = createUserViewModel::onPasswordChange,
        onNextClick = {
            createUserViewModel.createUser()
            if (uiState.couldNavigate) {
                onNextClick()
            }
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
            LoginTextField(
                inputValue = uiState.inputUserName.name,
                onValueChange = onNameChange,
                isEnabled = uiState.inputUserName.isFieldEnabled,
                placeholder = stringResource(id = R.string.user_name),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                keyboardActions = KeyboardActions { keyboardController?.hide() }
            )
            Spacer(modifier = Modifier.height(16.dp))
            LoginTextField(
                inputValue = uiState.inputPassword.password,
                onValueChange = onPasswordChange,
                isEnabled = uiState.inputPassword.isFieldEnabled,
                placeholder = stringResource(id = R.string.enter_password_hint),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions { keyboardController?.hide() }
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthButton(
                text = stringResource(id = R.string.continue_text),
                color = AppTheme.colors.backgroundOnSecondary,
                style = AppTheme.typography.text14M,
                onClick = onNextClick
            )
        }
    }
}

@Preview
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
