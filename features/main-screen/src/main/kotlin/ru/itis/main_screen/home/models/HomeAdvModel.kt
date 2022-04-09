package ru.itis.main_screen.home.models

import androidx.compose.runtime.Stable

/**
 * Created by Iskandar on 08.04.2022.
 */

@Stable
data class HomeAdvModel(
    val age: Int,
    val name: String,
    val city: String
)
