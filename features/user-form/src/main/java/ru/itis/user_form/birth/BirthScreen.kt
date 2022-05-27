package ru.itis.user_form.birth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.domain.models.User
import ru.itis.core.ui.R
import ru.itis.core.ui.components.AppTextField
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 27.05.2022 Created by Iskandar
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun BirthScreen(user: User, keyboardController: SoftwareKeyboardController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 52.dp, start = 52.dp, end = 16.dp)
    ) {
        Text(
            text = "Привет, ${user.name} " + stringResource(id = R.string.my_birthday),
            style = AppTheme.typography.text28M,
            textAlign = TextAlign.Start
        )
        AppTextField(text = "enter name", placeholder = "enter name")
    }
}

@Preview
@Composable
private fun BirthScreenPreview() {
//    BirthScreen(user = User(), )
}
