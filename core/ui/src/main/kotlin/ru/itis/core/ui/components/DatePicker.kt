package ru.itis.core.ui.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import ru.itis.core.ui.R

/**
 * Copyright (c) 27.06.2022 Created by Iskandar
 */


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePicker(
    dialogState: MaterialDialogState = rememberMaterialDialogState(),
    positiveButtonText: String = stringResource(id = R.string.ok),
    negativeButtonText: String = stringResource(id = R.string.cancel)
) {
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(positiveButtonText)
            negativeButton(negativeButtonText)
        }
    ) {
        datepicker { date ->
            Log.e("DEBUG", date.year.toString())
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun DatePickerPreview() {
    DatePicker()
}