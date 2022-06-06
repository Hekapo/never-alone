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
import ru.itis.core.ui.common.FieldCorrectnessCheck
import ru.itis.core.ui.components.AppTextField
import ru.itis.core.ui.components.AuthButton
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

    PhoneTabScreen(
        uiState = uiState,
        onNextClick = onNextClick,
        onPhoneChange = onPhoneChange,
    )

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun PhoneTabScreen(
    uiState: SignUpUIState,
    onNextClick: () -> Unit,
    onPhoneChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier.height(140.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(24.dp))

        AppTextField(
            text = uiState.inputPhone.phone,
            placeholder = stringResource(id = ru.itis.core.ui.R.string.enter_phone_hint),
            onChange = onPhoneChange,
            isError = uiState.inputPhone.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            keyboardActions = KeyboardActions { keyboardController?.hide() },
            isEnabled = uiState.inputPhone.isFieldEnabled
        )

        Spacer(modifier = Modifier.height(16.dp))
        AuthButton(
            text = stringResource(id = ru.itis.core.ui.R.string.create_account),
            style = AppTheme.typography.text14M,
            enabled = uiState.inputPhone.isError is FieldCorrectnessCheck.Success,
            onClick = { onNextClick() }
        )
    }
}
