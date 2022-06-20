package ru.itis.core.ui.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Created by Iskandar on 02.06.2022.
 */

object Gradients {
    val blackGradient = Brush.verticalGradient(
        colors = listOf(
            GradientColor.transparent,
            GradientColor.color3,
            GradientColor.color2,
            GradientColor.color1,
            GradientColor.black,
            GradientColor.black,
            GradientColor.black,
            GradientColor.black,
            GradientColor.black,
            GradientColor.black,

            )
    )
}

private object GradientColor {
    val transparent = Color.Transparent
    val black = Color.Black
    val color1 = Color(color = 0xDE000000)
    val color2 = Color(color = 0x99000000)
    val color3 = Color(color = 0x52000000)
}

