package ru.itis.user_form.add_photo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.components.PhotoHolder
import ru.itis.core.ui.theme.AppTheme
import ru.itis.user_form.UserFormViewModel

/**
 * Copyright (c) 27.05.2022 Created by Iskandar
 */

@Composable
internal fun AddPhotoScreenRoute(
    userFormViewModel: UserFormViewModel,
    onPickPhoto: (Int) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit,
) {
    val photoState by userFormViewModel.photoUris.collectAsState()

    AddPhotoScreen(
        photoState = photoState,
        onPickPhoto = {
            userFormViewModel.addPhoto(position = it, "none")
            onPickPhoto(it)
        },
        onNext = onNext,
        onBack = onBack,
    )
}


@OptIn(ExperimentalPermissionsApi::class, ExperimentalCoroutinesApi::class)
@Composable
private fun AddPhotoScreen(
    onPickPhoto: (position: Int) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit,
    photoState: MutableSet<Pair<Int?, String?>>,
) {

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 32.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.add_photo),
                style = AppTheme.typography.text36R,
                color = AppTheme.colors.textHighEmphasis,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.padding(top = 68.dp),
                text = stringResource(id = R.string.add_at_least_2_photo),
                style = AppTheme.typography.text16R,
                color = AppTheme.colors.textMediumEmphasis,
                textAlign = TextAlign.Start
            )
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    repeat(3) {
                        photoState.forEach { pair ->
                            uri =
                                if (!pair.second.equals("none") && pair.first != null && it == pair.first) {
                                    pair.second.toString()
                                } else {
                                    ""
                                }
                        }
                        PhotoHolder(
                            image = uri,
                            onClick = {
                                onPickPhoto(it)
                            })
                    }
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                AuthButton(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = stringResource(id = R.string.continue_text)
                ) {
                    onNext()
                }
            }
        }
    }
}

//@Preview
//@Preview(uiMode = UI_MODE_NIGHT_YES)
//@Composable
//private fun AddPhotoScreenPreview() {
//    AddPhotoScreen(onPickPhoto = {}, onNext = {}, onBack = {}, photoState = )
//}
