package ru.itis.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.itis.core.ui.R

/**
 * Created by Iskandar on 07.03.2022.
 */

private val Montserrat = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semi_bold, FontWeight.SemiBold),
)

@Immutable
data class AppTypography internal constructor(

    val text14M: TextStyle = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 22.sp,
    ),
    val text12R: TextStyle = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    val text14R: TextStyle = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    val text36R: TextStyle = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 40.sp,
    ),
    val text28R:TextStyle = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 30.sp,
    ),
    val text28M:TextStyle = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 30.sp,
    ),
    val text20M:TextStyle = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 22.sp,
    ),
    val text16M:TextStyle = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 18.sp,
    ),
    val text16R:TextStyle = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp,
    ),
)

internal val LocalAppTypography = staticCompositionLocalOf { AppTypography() }
