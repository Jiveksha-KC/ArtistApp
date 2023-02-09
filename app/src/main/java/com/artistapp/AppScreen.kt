package com.artistapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.artistapp.ui.theme.ArtistAppTheme
import com.artistapp.view.component.AppNavContainer
import com.artistapp.view.model.TopLevelAppBar

@Composable
fun AppScreen() {
    val navController = rememberNavController()
    var topLevelAppBar by remember { mutableStateOf(TopLevelAppBar()) }

    ArtistAppTheme(darkTheme = isSystemInDarkTheme()) {
        Scaffold(
            topBar = { topLevelAppBar.content() }
        ) { innerPadding ->
            AppNavContainer(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                setTopLevelAppBar = { content ->
                    topLevelAppBar = TopLevelAppBar(content)
                }
            )
        }
    }
}