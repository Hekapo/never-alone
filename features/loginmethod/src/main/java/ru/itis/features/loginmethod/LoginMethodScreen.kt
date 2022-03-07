package ru.itis.features.loginmethod

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 06.03.2022.
 */

@Composable
fun LoginMethodRoute(onSignInScreen: () -> Unit, onSignUpScreen: () -> Unit) {


}

@Composable
private fun LoginMethodScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.title1,
            color = AppTheme.colors.textHighEmphasis
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /*TODO*/ },
            colors = buttonColors(backgroundColor = AppTheme.colors.buttonOnPrimary)
        ) { 
            Text(text = "Test", color = AppTheme.colors.textHighEmphasis)
        }
        Button(
            onClick = { /*TODO*/ }
        ) { }

    }
}

@Preview(name = "Light mode", showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LoginMethodScreenPreview() {
    LoginMethodScreen()

}
