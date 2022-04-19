package ru.itis.main_screen.profile

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 04.04.2022.
 */

@Composable
fun ProfileRowInfo(
    title: String,
    titleColor: Color = AppTheme.colors.textMediumEmphasis,
    titleTextStyle: TextStyle = AppTheme.typography.text16M,
    mainText: String,
    mainTextColor: Color = AppTheme.colors.textHighEmphasis,
    mainTextStyle: TextStyle = AppTheme.typography.text20M,
    needRow: Boolean = true,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.backgroundPrimary),
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = title,
            color = titleColor,
            style = titleTextStyle
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = mainText,
            color = mainTextColor,
            style = mainTextStyle
        )
        if (needRow) {
            Divider(modifier = Modifier.padding(top = 8.dp, bottom = 4.dp).background(color = AppTheme.colors.textLowEmphasis))
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileRowInfoPreview() {
    Column {
        ProfileRowInfo(
            title = stringResource(id = R.string.full_name),
            mainText = "Iskandar",
            needRow = true
        )
        ProfileRowInfo(
            title = stringResource(id = R.string.email_text),
            mainText = "isk11@gmail.com",
            needRow = true
        )
        ProfileRowInfo(
            title = stringResource(id = R.string.social_url),
            mainText = "https:\\instagram.com",
            needRow = true
        )
    }

}