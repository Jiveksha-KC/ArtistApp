package com.artistapp.ui.theme

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.artistapp.ui.util.widthSizeClass

val genericHorizontalContentPadding
    @Composable
    get() = when (widthSizeClass) {
        WindowWidthSizeClass.Expanded -> 128.dp
        WindowWidthSizeClass.Medium -> 64.dp
        else -> 16.dp
    }