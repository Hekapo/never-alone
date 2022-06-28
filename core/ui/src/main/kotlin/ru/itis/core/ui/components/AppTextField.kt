package ru.itis.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.itis.core.ui.R
import ru.itis.core.ui.common.FieldCorrectnessCheck
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
    error: String = "",
    isError: FieldCorrectnessCheck = FieldCorrectnessCheck.None,
    imeAction: ImeAction = ImeAction.Next,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions = KeyboardActions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isEnabled: Boolean = true
) {
    Column {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = text,
            onValueChange = onChange,
            leadingIcon = leadingIcon,
            textStyle = AppTheme.typography.text16M,
            isError = when (isError) {
                is FieldCorrectnessCheck.None -> {
                    false
                }
                is FieldCorrectnessCheck.Error -> {
                    true
                }
                is FieldCorrectnessCheck.Success -> {
                    false
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction,
                keyboardType = keyboardOptions.keyboardType
            ),
            keyboardActions = keyboardActions,
            enabled = isEnabled,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = AppTheme.colors.textHighEmphasis,
                focusedBorderColor = AppTheme.colors.textHighEmphasis,
                unfocusedBorderColor = AppTheme.colors.textMediumEmphasis,
                disabledBorderColor = AppTheme.colors.textMediumEmphasis,
                disabledTextColor = AppTheme.colors.textHighEmphasis
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(fontSize = 18.sp, color = AppTheme.colors.textLowEmphasis)
                )
            }
        )
        if (isError is FieldCorrectnessCheck.Error) {
            Text(
                text = error.ifEmpty { stringResource(id = isError.message) },
                color = AppTheme.colors.errorOnPrimary,
                style = AppTheme.typography.text12R,
                modifier = Modifier.padding(start = 16.dp, top = 0.dp)
            )
        }
    }
}

@Preview
@Composable
fun AppTextFieldPreview() {
    AppTextField(text = "Continue", placeholder = "name", onChange = {})
}

@Preview
@Composable
fun AppTextFieldErrorPreview() {
    AppTextField(
        text = "Continue",
        placeholder = "name",
        onChange = {},
        isError = FieldCorrectnessCheck.Error(R.string.input_field_empty_error),
        error = "Text field cannot be empty"
    )
}