package ru.itis.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 22.04.2022 Created by Iskandar
 */


@Composable
fun BasicTextField(
    inputValue: String,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    BasicTextField(
        value = inputValue,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            Row(modifier = Modifier.fillMaxWidth()) {
                if (inputValue.isBlank() || inputValue.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = AppTheme.colors.textFieldOnPrimary,
                        fontSize = 14.sp
                    )
                }
            }
            innerTextField()
        }
    )
}

@Preview
@Composable
fun Preview() {
    BasicTextField(inputValue = " ", placeholder = "fuck", onValueChange = {})
}