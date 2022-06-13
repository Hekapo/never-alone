package ru.itis.features.signup.login_method

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.components.NoInternetWarn
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 06.03.2022.
 */

@Composable
fun LoginMethodRoute(
    deps: LoginMethodDeps,
    onSignInScreen: () -> Unit,
    onSignUpScreen: () -> Unit
) {

    val loginMethodComponentViewModel = viewModel<LoginMethodComponentViewModel>(
        factory = LoginMethodComponentViewModelFactory(deps)
    )

    val loginMethodViewModel = viewModel<LoginMethodViewModel>(
        factory = loginMethodComponentViewModel.loginMethodComponent.viewModelFactory
    )

    val internetAvailable by loginMethodViewModel.isNetAvailable.collectAsState()

    LoginMethodScreen(internetAvailable = internetAvailable, onSignInScreen, onSignUpScreen)
}

@Composable
private fun LoginMethodScreen(
    internetAvailable: Boolean,
    onSignInScreen: () -> Unit,
    onSignUpScreen: () -> Unit,
) {

    Box(modifier = Modifier.fillMaxSize()) {
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
                onClick = onSignUpScreen
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthButton(
                text = stringResource(id = R.string.signin),
                onClick = onSignInScreen
            )
        }
        AnimatedVisibility(
            visible = !internetAvailable,
            enter = expandVertically(expandFrom = Alignment.Top),
            exit = slideOut(targetOffset = { fullSize ->
                IntOffset(
                    0,
                    -fullSize.height
                )
            })
        ) {
            NoInternetWarn()
        }
    }
}

@Preview(name = "Light mode")
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LoginMethodScreenPreview() {
    LoginMethodScreen(internetAvailable = true, onSignInScreen = {}, onSignUpScreen = {})

}
