package com.artistapp.ui.styles

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.artistapp.ui.theme.ArtistAppTheme

/**
 * IMPLEMENTATION
 */

@Composable
fun StyledTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    StyledTopAppBar(
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
        },
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        elevation = elevation
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyledTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    CenterAlignedTopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colors.primary
        )
    )
}

/**
 * PREVIEW
 */

@Preview(name = "StyledTopAppBarPreview Light")
@Preview(name = "StyledTopAppBarPreview Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun StyledTopAppBarPreview() {
    ArtistAppTheme {
        StyledTopAppBar(title = "Some toolbar")
    }
}