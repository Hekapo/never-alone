package ru.itis.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme


/**
 * Created by Iskandar on 13.03.2022.
 */

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    text: String = "Sign In with Google",
    loadingText: String = "Creating Account...",
    icon: Int = R.drawable.ic_google_logo,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = RoundedCornerShape(3.dp),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    backgroundColor: Color = AppTheme.colors.googleButton,
//    progressIndicatorColor: Color = MaterialTheme.colors.primary,
    onClicked: () -> Unit
) {
    var clicked by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .clickable { clicked = !clicked },
        shape = shape,
        color = backgroundColor,
        border = if (isSystemInDarkTheme()) BorderStroke(
            width = 0.dp,
            color = Color.Transparent
        ) else BorderStroke(width = 1.dp, color = Color.LightGray),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 2.dp,
                    top = 2.dp,
                    bottom = 2.dp,
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color.White)
                    .size(44.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Google Button",
                    tint = Color.Unspecified
                )

            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = if (clicked) loadingText else text,
                color = AppTheme.colors.googleButtonText
            )
            if (clicked) {
//                TODO progress indicator not working
//                Spacer(modifier = Modifier.width(16.dp))
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .height(16.dp)
//                        .width(16.dp),
//                    strokeWidth = 2.dp,
//                    color = progressIndicatorColor
//                )
                onClicked()
            }
        }
    }
}

@Composable
@Preview(name = "GoogleButtonPreview (light)")
@Preview(uiMode = UI_MODE_NIGHT_YES, name = "GoogleButtonPreview (dark)")
private fun GoogleButtonPreview() {
    GoogleButton(
        text = "Sign In with Google",
        loadingText = "Creating Account...",
        onClicked = {}
    )
}