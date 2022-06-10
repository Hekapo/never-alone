package ru.itis.features.signup.login_method

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 06.03.2022.
 */

@Composable
fun LoginMethodRoute(onSignInScreen: () -> Unit, onSignUpScreen: () -> Unit) {

    LoginMethodScreen(onSignInScreen, onSignUpScreen)
}

@Composable
private fun LoginMethodScreen(onSignInScreen: () -> Unit, onSignUpScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.app_name),
            style = AppTheme.typography.text36R,
            textAlign = TextAlign.Center,
            color = AppTheme.colors.textHighEmphasis
        )
        Spacer(modifier = Modifier.height(48.dp))
        AuthButton(
            text = stringResource(id = R.string.create_account),
            style = AppTheme.typography.text14M
        ) {
            onSignUpScreen()
        }
        Spacer(modifier = Modifier.height(16.dp))
        AuthButton(
            text = stringResource(id = R.string.signin),
            style = AppTheme.typography.text14M
        ) {
            onSignInScreen()
        }
    }
}

@Preview(name = "Light mode", showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LoginMethodScreenPreview() {
    LoginMethodScreen({}, {})

}
