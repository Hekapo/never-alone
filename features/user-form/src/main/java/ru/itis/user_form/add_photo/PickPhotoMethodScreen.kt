package ru.itis.user_form.add_photo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme
import ru.itis.user_form.UserFormViewModel
import ru.itis.user_form.add_photo.gallery.GallerySelect

/**
 * Created by Iskandar on 01.06.2022.
 */

@Composable
internal fun PickPhotoMethodScreenRoute(
    userFormViewModel: UserFormViewModel,
    onBack: () -> Unit,
) {

    PickPhotoMethodScreen(
        addUri = { uri ->

        },
        onBack = onBack,
    )

}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalCoroutinesApi::class)
@Composable
private fun PickPhotoMethodScreen(
    addUri: (String) -> Unit,
    onBack: () -> Unit,
) {

    var isCameraOpened by remember {
        mutableStateOf(false)
    }

    var isGalleryOpened by remember {
        mutableStateOf(false)
    }
    var uri by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(color = AppTheme.colors.backgroundPrimary)
    ) {
        IconButton(
            onClick = {
                onBack()
            },
            content = {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = AppTheme.colors.textHighEmphasis
                )
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            PickRow(icon = R.drawable.camera, text = "Camera") {
                isCameraOpened = true
                isGalleryOpened = false

            }

            PickRow(icon = R.drawable.gallery, text = "Gallery") {
                isGalleryOpened = true
                isCameraOpened = false
            }
            if (isCameraOpened) {
                CameraCapture(
                    onImageFile = {
                        isCameraOpened = false
                        uri = it.toUri().toString()
                        addUri(uri)
                    }
                )
            }
            if (isGalleryOpened) {
                GallerySelect(
                    onImageUri = {
                        isGalleryOpened = false
                        uri = it.toString()
                        addUri(uri)
                    }
                )
            }
        }
    }
}

@Composable
private fun PickRow(
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
@Composable
private fun PickPhotoMethodScreenPreview() {
    PickPhotoMethodScreen(
        addUri = {},
        onBack = {}
    )
}
