package ru.itis.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
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
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        modifier = modifier
            .heightIn(min = 46.dp)
            .fillMaxWidth(),
        value = inputValue,
        onValueChange = onValueChange,
        textStyle = AppTheme.typography.text14M,
        label = label,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        isError = isError,
        enabled = isEnabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        singleLine = true,
        maxLines = 1,
        placeholder = {
            Text(
                text = placeholder,
                style = AppTheme.typography.text14M,
                color = AppTheme.colors.textLowEmphasis
            )
        },
        shape = RoundedCornerShape(3.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            textColor = AppTheme.colors.textMediumEmphasis,
            focusedLabelColor = AppTheme.colors.textMediumEmphasis,
            unfocusedLabelColor = AppTheme.colors.textMediumEmphasis,
            disabledTextColor = AppTheme.colors.textHighEmphasis.copy(alpha = 0.5f),
        )
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TextFieldPreview() {
//    LoginTextField(
//        inputValue = "Test input",
//        placeholder = "Test",
//        onValueChange = {},
//        isError = true,
//        label = {
//            Text(text = "error")
//        }
//    )

    OutlinedTextField(
        value = "text",
        onValueChange = {},
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = AppTheme.colors.textFieldOnPrimary,
            textColor = AppTheme.colors.textMediumEmphasis

        )
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TextFieldPreview2() {
//    LoginTextField(
//        inputValue = "Test input",
//        placeholder = "Test",
//        onValueChange = {},
//        isError = true,
//        label = {
//            Text(text = "error")
//        }
//    )

    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = { Text(text = "empty field") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = AppTheme.colors.textFieldOnPrimary,
            textColor = AppTheme.colors.textMediumEmphasis,
        ),
        isError = true
    )
}
