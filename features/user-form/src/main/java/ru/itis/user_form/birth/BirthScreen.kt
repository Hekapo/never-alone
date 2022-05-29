package ru.itis.user_form.birth

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.domain.models.User
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AppTextField
import ru.itis.core.ui.theme.AppTheme
import ru.itis.user_form.UserFormViewModel

/**
 * Copyright (c) 27.05.2022 Created by Iskandar
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun BirthScreenRoute(
    user: User,
    keyboardController: SoftwareKeyboardController?,
    userFormViewModel: UserFormViewModel
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
        }
    )
}

@Composable
private fun BirthScreen(
    user: User,
    dateState: String,
    showDatePickerDialog: (Context) -> Unit,
    setBirthDate: (String) -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.backgroundPrimary)
            .padding(top = 52.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Привет, ${user.name} " + stringResource(id = R.string.my_birthday),
            style = AppTheme.typography.text28M,
            textAlign = TextAlign.Start
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 152.dp),
            contentAlignment = Alignment.Center
        ) {
            AppTextField(
                modifier = Modifier.clickable {
                    showDatePickerDialog(context)
                },
                text = dateState,
                placeholder = "Birthdate",
                isEnabled = false,
                onChange = {
                    setBirthDate(it)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BirthScreenPreview() {
    BirthScreen(
        user = User(),
        dateState = "",
        showDatePickerDialog = {},
        setBirthDate = {}
    )
}
