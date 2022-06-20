package ru.itis.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 20.04.2022 Created by Iskandar
 */

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    backgroundColor: Color = AppTheme.colors.textFieldOnPrimary,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 16.dp),
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp
    ) {
        Box(modifier = Modifier.clickable(onClick = onClick)) {
            content()
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchFieldPreview() {
    SearchField(
        onClick = {},
        content = {}
    )
}
