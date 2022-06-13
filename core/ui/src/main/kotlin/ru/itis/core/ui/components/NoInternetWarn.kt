package ru.itis.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme

@Composable
fun NoInternetWarn(
    text: String = stringResource(id = R.string.no_internet),
    backgroundColor: Color = AppTheme.colors.textHighEmphasis,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(color = backgroundColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
            color = AppTheme.colors.backgroundPrimary,
            strokeWidth = 2.dp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text, color = AppTheme.colors.backgroundPrimary,
            style = AppTheme.typography.text14R
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NoInternetWarnPreview() {
    NoInternetWarn()
}
