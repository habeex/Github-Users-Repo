package com.example.githubrepo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.githubrepo.R

val Manrope = FontFamily(
    fonts = listOf(
        Font(R.font.manrope_regular, FontWeight.Normal),
        Font(R.font.manrope_bold, FontWeight.W700),
        Font(R.font.manrope_semibold, FontWeight.W600),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = Manrope,
        fontWeight = FontWeight.Normal,
        lineHeight = 13.66.sp,
    ),
    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontFamily = Manrope,
        fontWeight = FontWeight.W600,
        lineHeight = 13.66.sp,
    ),
    titleLarge = TextStyle(
        fontSize = 18.sp,
        fontFamily = Manrope,
        fontWeight = FontWeight.W700,
        lineHeight = 13.66.sp,
    )
)