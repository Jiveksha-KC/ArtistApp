package com.artistapp.view.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.vector.ImageVector
import com.artistapp.resource.R

sealed class Screen(val route: String) {

    companion object {
        val topLevelNavigationItems = listOf(Search)
    }

    /**
     * TOP LEVEL SCREENS
     */
    abstract class TopLevelScreen(
        route: String,
        @StringRes val titleRes: Int,
        val iconVector: ImageVector
    ) : Screen(route)

    object Search : TopLevelScreen(
        route = "search",
        titleRes = R.string.search_title,
        iconVector = Icons.Default.Face
    )

    object SavingsGoal : Screen(route = "Artist")
}