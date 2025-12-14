package com.example.plantwatering.presentation.model.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.plantwatering.R

// Set of Material typography styles to start with
val testFamily = FontFamily(
    Font(R.font.gowunbatang_bold)
)

//val AppTypography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = testFamily,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
//)
val base = Typography()
val AppTypography = base.copy(
    displayLarge = base.displayLarge.copy(fontFamily = testFamily),
    displayMedium = base.displayMedium.copy(fontFamily = testFamily),
    displaySmall = base.displaySmall.copy(fontFamily = testFamily),
    headlineLarge = base.headlineLarge.copy(fontFamily = testFamily),
    headlineMedium = base.headlineMedium.copy(fontFamily = testFamily),
    headlineSmall = base.headlineSmall.copy(fontFamily = testFamily),
    titleLarge = base.titleLarge.copy(fontFamily = testFamily),
    titleMedium = base.titleMedium.copy(fontFamily = testFamily),
    titleSmall = base.titleSmall.copy(fontFamily = testFamily),
    bodyLarge = base.bodyLarge.copy(fontFamily = testFamily),
    bodyMedium = base.bodyMedium.copy(fontFamily = testFamily),
    bodySmall = base.bodySmall.copy(fontFamily = testFamily),
    labelLarge = base.labelLarge.copy(fontFamily = testFamily),
    labelMedium = base.labelMedium.copy(fontFamily = testFamily),
    labelSmall = base.labelSmall.copy(fontFamily = testFamily),
)

/*
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = testFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)*/