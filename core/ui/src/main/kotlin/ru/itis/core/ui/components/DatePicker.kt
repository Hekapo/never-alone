package ru.itis.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import ru.itis.core.ui.R
import ru.itis.core.ui.theme.AppTheme

/**
 * Copyright (c) 27.06.2022 Created by Iskandar
 */


@Composable
fun DatePicker(
    dialogState: MaterialDialogState = rememberMaterialDialogState(initialValue = true),
    positiveButtonText: String = stringResource(id = R.string.ok),
    negativeButtonText: String = stringResource(id = R.string.cancel)
) {
    MaterialDialog(
        dialogState = dialogState,
        backgroundColor = AppTheme.colors.backgroundPrimary,
        buttons = {
            positiveButton(positiveButtonText)
            negativeButton(negativeButtonText)
        }
    ) {
        datepicker(
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = AppTheme.colors.buttonOnPrimary,
                dateActiveBackgroundColor = AppTheme.colors.buttonOnPrimary,
                dateInactiveTextColor = AppTheme.colors.textHighEmphasis,
                calendarHeaderTextColor = AppTheme.colors.backgroundOnSecondary
            )
        ) { date ->
            Log.e("DEBUG", date.year.toString())
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DatePickerPreview() {
    DatePicker()
}