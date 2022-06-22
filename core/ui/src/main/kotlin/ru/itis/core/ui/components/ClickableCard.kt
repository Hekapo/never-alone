package ru.itis.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 28.05.2022.
 */

@Composable
fun ClickableCard(
    modifier: Modifier,
    text: String,
    selectedValue: String,
    elevation: Dp = 0.dp,
    onSelect: (String) -> Unit
) {

    val colorWhenClicked =
        if (text == selectedValue) AppTheme.colors.textHighEmphasis else AppTheme.colors.textLowEmphasis

    Card(
        modifier = modifier
            .selectable(
                selected = (text == selectedValue),
                onClick = {
                    onSelect(text)
                }
            ),
        backgroundColor = Color.White.copy(alpha = 0f),
        border = BorderStroke(
            width = 1.dp,
            color = colorWhenClicked
        ),
        elevation = elevation
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 2.dp),
                text = text,
                style = AppTheme.typography.text14M,
                color = colorWhenClicked,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ClickableCardGendersPreview() {
    ClickableCard(
        modifier = Modifier.fillMaxWidth(),
        text = "Sport",
        selectedValue = "Sport",
        onSelect = {}
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ClickableCardInterestsPreview() {
    ClickableCard(
        modifier = Modifier.wrapContentWidth(),
        text = "Sport",
        selectedValue = "Sport",
        onSelect = {}
    )
}
