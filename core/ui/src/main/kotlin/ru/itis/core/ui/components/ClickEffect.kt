package ru.itis.core.ui.components

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Iskandar on 06.04.2022.
 */

fun Modifier.repeatingClickable(
    enabled: Boolean = true,
    maxDelayMillis: Long = 1000,
    minDelayMillis: Long = 5,
    delayDecayFactor: Float = .20f,
    onClick: () -> Unit
): Modifier = composed {

    val currentClickListener by rememberUpdatedState(onClick)
    val interactionSource = remember { MutableInteractionSource() }

    pointerInput(interactionSource, enabled) {
        forEachGesture {
            coroutineScope {
                awaitPointerEventScope {
                    val down = awaitFirstDown(requireUnconsumed = false)
                    // Create a down press interaction
                    val downPress = PressInteraction.Press(down.position)
                    val heldButtonJob = launch {
                        // Send the press through the interaction source
                        interactionSource.emit(downPress)
                        var currentDelayMillis = maxDelayMillis
                        while (enabled && down.pressed) {
                            currentClickListener()
                            delay(currentDelayMillis)
                            val nextMillis =
                                currentDelayMillis - (currentDelayMillis * delayDecayFactor)
                            currentDelayMillis = nextMillis.toLong().coerceAtLeast(minDelayMillis)
                        }
                    }
                    val up = waitForUpOrCancellation()
                    heldButtonJob.cancel()
                    // Determine whether a cancel or release occurred, and create the interaction
                    val releaseOrCancel = when (up) {
                        null -> PressInteraction.Cancel(downPress)
                        else -> PressInteraction.Release(downPress)
                    }
                    launch {
                        // Send the result through the interaction source
                        interactionSource.emit(releaseOrCancel)
                    }
                }
            }
        }
    }.indication(interactionSource, rememberRipple())
}
