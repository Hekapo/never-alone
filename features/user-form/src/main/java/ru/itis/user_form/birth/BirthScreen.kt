package ru.itis.user_form.birth

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import ru.itis.core.domain.models.User
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AppTextField
import ru.itis.core.ui.components.AuthButton
import ru.itis.core.ui.components.DatePicker
import ru.itis.core.ui.theme.AppTheme
import ru.itis.user_form.UserFormViewModel

/**
 * Copyright (c) 27.05.2022 Created by Iskandar
 */

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun BirthScreenRoute(
    user: User,
    userFormViewModel: UserFormViewModel,
    onNext: () -> Unit,
) {

    val dateState by userFormViewModel.dateOfBirth.collectAsState()

    BirthScreen(
        user = user,
        dateState = dateState,
        showDatePickerDialog = {
            userFormViewModel.showDatePickerDialog(it)
        },
        setBirthDate = {
            userFormViewModel.setBirthdayDate(date = it)
        },
        onNext = onNext,
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun BirthScreen(
    user: User,
    dateState: String,
    showDatePickerDialog: (Context) -> Unit,
    setBirthDate: (String) -> Unit,
    onNext: () -> Unit,
) {

    val dialogState = rememberMaterialDialogState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(color = AppTheme.colors.backgroundPrimary)
    ) {
        DatePicker(
            dialogState = dialogState
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 22.dp, start = 16.dp),
                text = stringResource(id = R.string.hello) + ", ${user.name} " + stringResource(id = R.string.my_birthday),
                style = AppTheme.typography.text36R,
                color = AppTheme.colors.textHighEmphasis,
                textAlign = TextAlign.Start
            )
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                AppTextField(
                    modifier = Modifier
                        .clickable {
                            dialogState.show()
                        },
                    text = dateState,
                    placeholder = "Birthdate",
                    onChange = {
                        setBirthDate(it)
                    },
                    isEnabled = false,
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                AuthButton(
                    text = stringResource(id = R.string.continue_text),
                    onClick = onNext
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun BirthScreenPreview() {
    BirthScreen(
        user = User(name = "Iskandar"),
        dateState = "",
        showDatePickerDialog = {},
        setBirthDate = {},
        onNext = {},
    )
}
