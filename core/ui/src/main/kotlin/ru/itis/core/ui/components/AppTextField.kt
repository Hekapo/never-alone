package ru.itis.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 27.05.2022 Created by Iskandar
 */

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        onValueChange = onChange,
        leadingIcon = leadingIcon,
        textStyle = AppTheme.typography.text16M,
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = keyBoardActions,
        enabled = isEnabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = AppTheme.colors.textHighEmphasis,
            unfocusedBorderColor = AppTheme.colors.textMediumEmphasis,
            disabledBorderColor = AppTheme.colors.textLowEmphasis,
            disabledTextColor = AppTheme.colors.textLowEmphasis
        ),
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(fontSize = 18.sp, color = AppTheme.colors.textLowEmphasis)
            )
        }

    )
}

@Preview
@Composable
fun AppTextFieldPreview() {
    AppTextField(text = "Continue", placeholder = "name")
}