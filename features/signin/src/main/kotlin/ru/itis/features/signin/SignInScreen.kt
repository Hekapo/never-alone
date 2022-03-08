package ru.itis.features.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import ru.itis.core.ui.R
import ru.itis.core.ui.components.ClickableIcon
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
    }


}

@Preview
@Composable
fun SignInPreview() {
    SignInScreen {

    }

}