@file:OptIn(ExperimentalComposeUiApi::class)

package ru.itis.features.signup.phone

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.components.LoginTextField
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 23.03.2022.
 */

@Composable
fun PhoneVerificationRoute(
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
) {

}

@Composable
private fun PhoneVerificationScreen(
    uiState: PhoneScreenUIState,
    onCodeChanged: (String) -> Unit,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    onRequestMoreClick: () -> Unit
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.enter_verification_code),
                style = AppTheme.typography.text14M,
                color = AppTheme.colors.textHighEmphasis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.enter_six_digit_code),
                style = AppTheme.typography.text14R,
                color = AppTheme.colors.textMediumEmphasis
            )
            Text(
                modifier = Modifier.clickable(
                    role = Role.Button,
                    onClick = onRequestMoreClick
                ),
                text = stringResource(R.string.request_more),
                style = AppTheme.typography.text14M,
                color = AppTheme.colors.textHighEmphasis,
            )
            Spacer(modifier = Modifier.height(16.dp))
            LoginTextField(
                inputValue = uiState.inputCode.code,
                placeholder = stringResource(id = R.string.verification_code),
                onValueChange = onCodeChanged
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthButton(
                text = stringResource(id = R.string.next),
                color = AppTheme.colors.backgroundOnSecondary,
                style = AppTheme.typography.text14M,
                onClick = onNextClick
            )

        }
    }

}

@Preview(locale = "en")
@Preview(uiMode = UI_MODE_NIGHT_YES, locale = "tt")
@Composable
fun PhoneVerificationScreenPreview() {
    PhoneVerificationScreen(
        uiState = PhoneScreenUIState(),
        onCodeChanged = {},
        onNextClick = {},
        onBackClick = {},
        onRequestMoreClick = {}
    )
}
