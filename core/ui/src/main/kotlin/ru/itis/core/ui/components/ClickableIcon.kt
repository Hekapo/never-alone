package ru.itis.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 07.03.2022.
 */

@Composable
fun ClickableIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    backgroundColor: Color,
    iconTint: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .background(backgroundColor)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = iconTint
        )
    }

}

@Preview
@Composable
fun ClickableScreenPreview() {
    ClickableIcon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Back",
        backgroundColor = AppTheme.colors.backgroundPrimary,
        iconTint = AppTheme.colors.errorOnPrimary
    ) {

    }
}