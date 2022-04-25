package ru.itis.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
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
    focusRequester: FocusRequester,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    BasicTextField(
        value = inputValue,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        textStyle = AppTheme.typography.text16M,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
            ) {
                if (inputValue.isBlank() || inputValue.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = AppTheme.colors.textMediumEmphasis,
                        style = AppTheme.typography.text16M,
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
fun Preview() {
    BasicTextField(
        inputValue = " ",
        placeholder = "fuck",
        onValueChange = {},
        focusRequester = FocusRequester()
    )
}

@Preview
@Composable
fun PreviewWithoutPlaceholder() {
    BasicTextField(
        inputValue = "hello",
        placeholder = "fuck",
        onValueChange = {},
        focusRequester = FocusRequester()
    )
}