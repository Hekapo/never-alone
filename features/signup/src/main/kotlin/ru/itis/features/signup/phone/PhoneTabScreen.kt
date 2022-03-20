package ru.itis.features.signup.phone

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
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.components.LoginTextField
import ru.itis.core.ui.theme.AppTheme
import ru.itis.features.signup.SignUpUIState

/**
 * Created by Iskandar on 20.03.2022.
 */

@Composable
fun PhoneRoute(
    uiState: SignUpUIState,
    onPhoneChange: (String) -> Unit,
    onNextClick: () -> Unit
) {

    PhoneScreen(
        uiState = uiState,
        onNextClick = onNextClick,
        onPhoneChange = onPhoneChange,
    )

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun PhoneScreen(
    uiState: SignUpUIState,
    onNextClick: () -> Unit,
    onPhoneChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier.height(140.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(24.dp))
        LoginTextField(
            inputValue = uiState.inputPhone.phone,
            onValueChange = onPhoneChange,
            isEnabled = uiState.inputPhone.isFieldEnabled,
            placeholder = stringResource(id = ru.itis.core.ui.R.string.enter_phone_hint),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions { keyboardController?.hide() },
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthButton(
            text = stringResource(id = ru.itis.core.ui.R.string.create_account),
            color = AppTheme.colors.backgroundOnSecondary,
            style = AppTheme.typography.text14M
        ) {
            onNextClick()
        }
    }
}
