@file:OptIn(ExperimentalAnimationApi::class)

package ru.itis.main_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.theme.AppTheme
import ru.itis.main_screen.main.destinations.MainBottomScreen
import ru.itis.main_screen.main.utils.Constants

/**
 * Created by Iskandar on 03.04.2022.
 */

@Composable
internal fun AnimatedBottomBar(
    destinations: List<MainBottomScreen> = Constants.MAIN_BOTTOM_SCREENS,
    currentRoute: MainBottomScreen = MainBottomScreen.Home,
    onItemSelected: (MainBottomScreen) -> Unit
) {
    Row(
        modifier = Modifier
            .background(AppTheme.colors.bottomBarOnPrimary)
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        destinations.forEach { item ->
            CustomBottomNavigationItem(item = item, isSelected = item == currentRoute) {
                onItemSelected(item)
            }
        }
    }
}

@Composable
private fun CustomBottomNavigationItem(
    item: MainBottomScreen,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val background =
        if (isSelected) AppTheme.colors.textMediumEmphasis.copy(alpha = 0.1f) else Color.Transparent
    val contentColor =
        if (isSelected) AppTheme.colors.textHighEmphasis else AppTheme.colors.textMediumEmphasis

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Icon(
                imageVector = item.icon,
                contentDescription = stringResource(id = item.resourceId),
                tint = contentColor
            )

            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = stringResource(id = item.resourceId),
                    color = contentColor
                )
            }
        }
    }
}


@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun CustomBottomNavigationPreview() {
    AnimatedBottomBar(
        Constants.MAIN_BOTTOM_SCREENS,
        MainBottomScreen.Home,
        onItemSelected = {}
    )
}
