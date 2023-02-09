package com.artistapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkMaterialColorPalette = darkColors(
    primary = Purple80,
    secondary = PurpleGrey80,
    onSecondary = Pink80
)

private val LightMaterialColorPalette = lightColors(
    primary = Purple40,
    secondary = PurpleGrey40,
    onSecondary = Pink40
)

@Composable
fun ArtistAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val materialColors = if (darkTheme) DarkMaterialColorPalette else LightMaterialColorPalette

    MaterialTheme(
        colors = materialColors,
        typography = ThemedTypography,
    ) {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !MaterialTheme.colors.isLight
        val statusBarColor = MaterialTheme.colors.primary

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = statusBarColor,
                darkIcons = useDarkIcons,
                isNavigationBarContrastEnforced = false
            )
        }

        content()
    }
}

