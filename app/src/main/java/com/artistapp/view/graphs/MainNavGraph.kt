package com.artistapp.view.graphs

import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.artistapp.artist.constants.ArtistDetailScreenConstants
import com.artistapp.artist.view.ArtistDetailsScreen
import com.artistapp.artist.viewmodel.ArtistDetailsViewModelImpl
import com.artistapp.extensions.navigateToScreen
import com.artistapp.search.view.ArtistSearchScreen
import com.artistapp.search.viewmodel.ArtistSearchViewModelImpl
import com.artistapp.view.model.Screen

fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
    setTopLevelAppBar: @Composable (content: @Composable () -> Unit) -> Unit
) {
    composable(route = Screen.Search.route) {
        ArtistSearchScreen(
            viewModel = hiltViewModel<ArtistSearchViewModelImpl>(),
            setTopLevelAppBar = setTopLevelAppBar,
            navigateToArtistInfo = {
                navController.navigateToScreen(
                    Screen.SavingsGoal.route,
                    bundleOf(ArtistDetailScreenConstants.ARTIST_ID to it)
                )
            }
        )
    }

    composable(route = Screen.SavingsGoal.route) {
        ArtistDetailsScreen(
            viewModel = hiltViewModel<ArtistDetailsViewModelImpl>(),
            setTopLevelAppBar = setTopLevelAppBar,
        )
    }
}