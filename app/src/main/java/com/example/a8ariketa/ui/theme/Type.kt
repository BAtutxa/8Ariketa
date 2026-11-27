package com.example.a8ariketa.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.example.a8ariketa.R

// TITULUETARAKO AR ONE SANS LETRA FAMILIA
private val ArOneSans = FontFamily(
    Font(R.font.ar_one_sans)
)

// TESTUETARAKO HEPTA SLAB LETRA FAMILIA
private val HeptaSlab = FontFamily(
    Font(R.font.hepta_slab)
)

// MATERIAL 3-KO JATORRIZKO TIPOGRAFIA GORDE
private val DefaultTypography = Typography()

// TIPOGRAFIA BERRIA APLIKAZIO OSORAKO
val Typography = Typography(

    // TITULU NAGUSIAK: AR ONE SANS

    displayLarge = DefaultTypography.displayLarge.copy(
        fontFamily = ArOneSans
    ),
    displayMedium = DefaultTypography.displayMedium.copy(
        fontFamily = ArOneSans
    ),
    displaySmall = DefaultTypography.displaySmall.copy(
        fontFamily = ArOneSans
    ),

    // GOIBURUAK: AR ONE SANS

    headlineLarge = DefaultTypography.headlineLarge.copy(
        fontFamily = ArOneSans
    ),
    headlineMedium = DefaultTypography.headlineMedium.copy(
        fontFamily = ArOneSans
    ),
    headlineSmall = DefaultTypography.headlineSmall.copy(
        fontFamily = ArOneSans
    ),

    // TITULU TXIKIAK: AR ONE SANS

    titleLarge = DefaultTypography.titleLarge.copy(
        fontFamily = ArOneSans
    ),
    titleMedium = DefaultTypography.titleMedium.copy(
        fontFamily = ArOneSans
    ),
    titleSmall = DefaultTypography.titleSmall.copy(
        fontFamily = ArOneSans
    ),

    // TESTUAK: HEPTA SLAB

    bodyLarge = DefaultTypography.bodyLarge.copy(
        fontFamily = HeptaSlab,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = DefaultTypography.bodyMedium.copy(
        fontFamily = HeptaSlab
    ),
    bodySmall = DefaultTypography.bodySmall.copy(
        fontFamily = HeptaSlab
    ),

    // ETIKETAK: HEPTA SLAB

    labelLarge = DefaultTypography.labelLarge.copy(
        fontFamily = HeptaSlab
    ),
    labelMedium = DefaultTypography.labelMedium.copy(
        fontFamily = HeptaSlab
    ),
    labelSmall = DefaultTypography.labelSmall.copy(
        fontFamily = HeptaSlab
    )

)

    /* Other default text styles to override
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
    */
