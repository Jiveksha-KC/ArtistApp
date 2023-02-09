package com.artistapp.view.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.artistapp.view.graphs.mainNavGraph
import com.artistapp.view.model.Screen

@Composable
fun AppNavContainer(
    modifier: Modifier,
    navController: NavHostController,
    setTopLevelAppBar: @Composable (content: @Composable () -> Unit) -> Unit
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Search.route
    ) {
        mainNavGraph(navController, setTopLevelAppBar)
    }
}