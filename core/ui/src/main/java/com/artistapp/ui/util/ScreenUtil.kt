package com.artistapp.ui.util

import android.content.res.Configuration
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.artistapp.ui.extension.findActivity

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
val widthSizeClass: WindowWidthSizeClass
    @Composable
    get() {
        val context = LocalContext.current
        return context.findActivity()?.let {
            calculateWindowSizeClass(it).widthSizeClass
        } ?: WindowWidthSizeClass.Compact
    }

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
val heightSizeClass: WindowHeightSizeClass
    @Composable
    get() {
        val context = LocalContext.current
        return context.findActivity()?.let {
            calculateWindowSizeClass(it).heightSizeClass
        } ?: WindowHeightSizeClass.Compact
    }

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}

@Composable
fun isLandscape() = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

@Composable
fun isLandscapeAndCompact() = isLandscape() && heightSizeClass == WindowHeightSizeClass.Compact
