package com.sbaygildin.promoshop.core.ui

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sbaygildin.promoshop.core.R

val SFUIText = FontFamily(
    Font(R.font.sf_ui_text_regular, FontWeight.Normal),
    Font(R.font.sf_ui_text_medium, FontWeight.Medium)
)
val Roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

val AppTypography = androidx.compose.material3.Typography(
    bodyLarge = TextStyle(
        fontFamily = SFUIText,
        fontSize = 21.sp,
        fontWeight = FontWeight.Medium
    ),
    bodySmall = TextStyle(
        fontFamily = SFUIText,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontFamily = SFUIText,
        fontSize = 16.sp,
        fontWeight = FontWeight.W500
    ),

    headlineLarge = TextStyle(
        fontFamily = SFUIText,
        fontSize = 31.sp,
        fontWeight = FontWeight.Bold
    ),
    labelSmall = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    labelLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

)
