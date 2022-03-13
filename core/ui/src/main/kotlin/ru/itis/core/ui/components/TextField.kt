package ru.itis.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 09.03.2022 Created by Iskandar
 */

@Composable
fun LoginTextField(
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
    TextField(
        modifier = modifier
            .heightIn(min = 46.dp)
            .fillMaxWidth(),
        value = inputValue,
        onValueChange = onValueChange,
        textStyle = AppTheme.typography.textField,
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
                style = AppTheme.typography.textField,
                color = AppTheme.colors.textLowEmphasis
            )
        },
        shape = RoundedCornerShape(3.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            textColor = AppTheme.colors.textMediumEmphasis,
            errorCursorColor = Color.Transparent,
            leadingIconColor = Color.Transparent,
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
            disabledTextColor = AppTheme.colors.textHighEmphasis.copy(alpha = 0.5f),
            disabledLabelColor = Color.Transparent,
            disabledLeadingIconColor = Color.Transparent,
            disabledTrailingIconColor = Color.Transparent
        )
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TextFieldPreview() {
    LoginTextField(inputValue = "Test input", placeholder = "Test", onValueChange = {})
}
