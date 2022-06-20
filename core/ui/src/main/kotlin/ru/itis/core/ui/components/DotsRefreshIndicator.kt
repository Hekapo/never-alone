package ru.itis.core.ui.components

import androidx.compose.animation.core.animate
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefreshState
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 07.04.2022.
 */

@Composable
fun DotsRefreshIndicator(
    state: SwipeRefreshState,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.textHighEmphasis,
    refreshingOffset: Dp = 16.dp,
    refreshTriggerDistance: Dp,
    elevation: Dp = 4.dp
) {
    val adjustedElevation = when {
        state.isRefreshing -> elevation
        state.indicatorOffset > 0.5f -> elevation
        else -> 0.dp
    }

    val indicatorRefreshTrigger = with(LocalDensity.current) { refreshTriggerDistance.toPx() }

    val indicatorHeight = with(LocalDensity.current) { 56.dp.roundToPx() }
    val refreshingOffsetPx = with(LocalDensity.current) { refreshingOffset.toPx() }
    
    var offset by remember { mutableStateOf(0f) }

    LaunchedEffect(state.isSwipeInProgress, state.isRefreshing) {
        // If there's no swipe currently in progress, animate to the correct resting position
        if (!state.isSwipeInProgress) {
            animate(
                initialValue = offset,
                targetValue = when {
                    state.isRefreshing -> indicatorHeight + refreshingOffsetPx
                    else -> 0f
                }
            ) { value, _ ->
                offset = value
            }
        }
    }

}

@Preview
@Composable
fun DotsRefreshIndicatorPreview() {

}
