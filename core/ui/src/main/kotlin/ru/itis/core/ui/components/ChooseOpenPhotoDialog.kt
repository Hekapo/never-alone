package ru.itis.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme

/**
 * Created by Iskandar on 31.05.2022.
 */

@Composable
fun ChooseOpenPhotoDialog(
    onDismiss: () -> Unit,
    openCamera: () -> Unit,
    openGallery: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .background(color = AppTheme.colors.backgroundPrimary)
                .clip(shape = RoundedCornerShape(2.dp))
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DialogRow(icon = R.drawable.camera, text = "Camera") {
                openCamera()
            }
            DialogRow(icon = R.drawable.gallery, text = "Gallery") {
                openGallery()
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                TextButton(onClick = onDismiss) {
                    Text(
                        text = "Cancel",
                        style = AppTheme.typography.text14R,
                        color = AppTheme.colors.textHighEmphasis
                    )
                }
            }

        }
    }
}

@Composable
private fun DialogRow(
    icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = true),
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(56.dp)
                .padding(16.dp),
            painter = painterResource(id = icon),
            contentDescription = text,
            tint = AppTheme.colors.textHighEmphasis
        )
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = text,
            style = AppTheme.typography.text16R,
            color = AppTheme.colors.textHighEmphasis
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ChooseOpenPhotoDialogPreview() {
    ChooseOpenPhotoDialog(
        onDismiss = {},
        openCamera = {},
        openGallery = {},
    )
}
