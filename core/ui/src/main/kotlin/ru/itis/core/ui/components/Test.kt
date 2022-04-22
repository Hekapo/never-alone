package ru.itis.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 22.04.2022 Created by Iskandar
 */



@Composable
fun TextField(
    modifier: Modifier = Modifier,
    inputValue: String,
    placeholder: String,
    backgroundColor: Color = AppTheme.colors.textFieldOnPrimary,
    isError: Boolean = false,
    isEnabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    androidx.compose.material.TextField(
        modifier = modifier
            .heightIn(min = 52.dp)
            .fillMaxWidth(),
        value = inputValue,
        onValueChange = onValueChange,
        textStyle = AppTheme.typography.text16M,
        label = null,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        isError = isError,
        enabled = isEnabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        maxLines = 1,
        placeholder = {
            Text(
                text = placeholder,
                style = AppTheme.typography.text14M,
                color = AppTheme.colors.textMediumEmphasis
            )
        },
        shape = RoundedCornerShape(26.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            textColor = AppTheme.colors.textLowEmphasis,
            cursorColor = AppTheme.colors.textMediumEmphasis,
            errorCursorColor = Color.Transparent,
            leadingIconColor = Color.Transparent,
            placeholderColor = AppTheme.colors.textMediumEmphasis,
            trailingIconColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = AppTheme.colors.textMediumEmphasis,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = AppTheme.colors.textMediumEmphasis,
            errorIndicatorColor = Color.Transparent,
            errorLabelColor = Color.Transparent,
            errorLeadingIconColor = Color.Transparent,
            errorTrailingIconColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledPlaceholderColor = AppTheme.colors.textMediumEmphasis,
            disabledTextColor = AppTheme.colors.textLowEmphasis.copy(alpha = 0.5f),
            disabledLabelColor = Color.Transparent,
            disabledLeadingIconColor = Color.Transparent,
            disabledTrailingIconColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun Preview() {
    TextField(inputValue = "hello", placeholder = "fuck", onValueChange ={})
}