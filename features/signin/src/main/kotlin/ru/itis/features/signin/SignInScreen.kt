@file:OptIn(ExperimentalComposeUiApi::class)
package ru.itis.features.signin

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.components.ClickableIcon
import ru.itis.core.ui.components.TextField
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 07.03.2022.
 */

@Composable
fun SignInRoute(
    onBackClick: () -> Unit
) {

    SignInScreen {
        onBackClick()
    }

}

@Composable
private fun SignInScreen(onBackClick: () -> Unit) {

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary)
    ) {
        ClickableIcon(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp),
            backgroundColor = AppTheme.colors.backgroundPrimary,
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back),
            iconTint = AppTheme.colors.errorOnPrimary,
            onClick = {
//                keyboardController?.hide()
                onBackClick()
            }
        )


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
                style = AppTheme.typography.title1,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.textHighEmphasis
            )
            Spacer(modifier = Modifier.height(48.dp))
            TextField(
                modifier = Modifier,
                inputValue = "Test",
                placeholder = "test",
                onValueChange = {})
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                modifier = Modifier,
                inputValue = "Test2",
                placeholder = "test",
                onValueChange = {})
            Spacer(modifier = Modifier.height(16.dp))
            AuthButton(
                text = stringResource(id = R.string.enter),
                color = AppTheme.colors.backgroundOnSecondary,
                style = AppTheme.typography.button
            ) {

            }
        }
    }


}

@Composable
fun ColumnScope.UserInfoInput(
    inputValue: String,
    keyboardController: SoftwareKeyboardController? = null,
    onValueChange: (String) -> Unit,
    onEnterClick: () -> Unit

) {

}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignInPreview() {
    SignInScreen {

    }

}