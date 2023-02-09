package com.artistapp.view.model

import androidx.compose.runtime.Composable
import com.artistapp.ui.components.LogoTopAppBar

data class TopLevelAppBar(
    val content: @Composable () -> Unit = { LogoTopAppBar() }
)