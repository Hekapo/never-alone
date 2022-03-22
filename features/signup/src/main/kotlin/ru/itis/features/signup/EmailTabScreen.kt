package ru.itis.features.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.components.LoginTextField
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 20.03.2022.
 */

@Composable
fun EmailRoute(
    uiState: SignUpUIState,
    onEmailChange: (String) -> Unit,
    onNextClick: () -> Unit
) {

    EmailScreen(
        uiState = uiState,
        onNextClick = onNextClick,
        onEmailChange = onEmailChange
    )

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun EmailScreen(
    uiState: SignUpUIState,
    onNextClick: () -> Unit,
    onEmailChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier.height(140.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(24.dp))
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
            style = AppTheme.typography.text14M
        ) {
            onNextClick()
        }
    }
}
