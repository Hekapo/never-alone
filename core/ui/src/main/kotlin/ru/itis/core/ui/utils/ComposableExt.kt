package ru.itis.core.ui.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * Created by Iskandar on 31.05.2022.
 */

@Composable
fun List<Int>.toComposeListOfString(): List<String> {
    val list = mutableListOf<String>()
    this.forEach {
        list.add(stringResource(id = it))
    }
    return list
}

fun List<Int>.toListOfString(context: Context): List<String> {
    val list = mutableListOf<String>()
    this.forEach {
        list.add(context.getString(it))
    }
    return list
}
