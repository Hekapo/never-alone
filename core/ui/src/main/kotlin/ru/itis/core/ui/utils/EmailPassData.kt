package ru.itis.core.ui.utils

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

/**
 * Created by Iskandar on 26.03.2022.
 */

@Immutable
@Parcelize
data class EmailPassData(
    val email: String
) : Parcelable
