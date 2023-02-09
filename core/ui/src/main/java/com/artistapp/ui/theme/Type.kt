package com.artistapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val ThemedTypography: Typography
    @Composable
    get() = Typography(
        defaultFontFamily = FontFamily.Default,
        h1 = MaterialTheme.typography.h1.copy(fontFamily = FontFamily.Monospace),
        h2 = MaterialTheme.typography.h2.copy(fontFamily = FontFamily.Monospace),
        h3 = MaterialTheme.typography.h3.copy(fontFamily = FontFamily.Monospace),
        h4 = MaterialTheme.typography.h4.copy(fontFamily = FontFamily.Monospace),
        h5 = MaterialTheme.typography.h5.copy(
            fontFamily = FontFamily.Monospace,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        ),
        h6 = MaterialTheme.typography.h6.copy(
            fontFamily = FontFamily.Monospace,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        ),
        body2 = MaterialTheme.typography.body2.copy(fontFamily = FontFamily.Monospace),
        button = MaterialTheme.typography.button.copy(
            fontFamily = FontFamily.Monospace,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        ),
        overline = MaterialTheme.typography.overline.copy(
            fontFamily = FontFamily.Monospace,
            letterSpacing = 0.8.sp
        )
    )