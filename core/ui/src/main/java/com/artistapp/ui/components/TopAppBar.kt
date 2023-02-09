package com.artistapp.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.artistapp.resource.R
import com.artistapp.ui.styles.StyledTopAppBar
import com.artistapp.ui.theme.ArtistAppTheme

/**
 * IMPLEMENTATION
 */

@Composable
fun LogoTopAppBar(
    title: String="",
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    StyledTopAppBar(
        title = title,
        navigationIcon = {
            Image(
                modifier = Modifier.padding(vertical = 8.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
            )
        },
        elevation = elevation
    )
}

@Composable
fun EmptyTopAppBar(
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    StyledTopAppBar(title = "", elevation = elevation)
}

/**
 * PREVIEW
 */

@Preview(name = "LogoTopAppBar Light")
@Preview(name = "LogoTopAppBar Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LogoTopAppBarPreview() {
    ArtistAppTheme {
        LogoTopAppBar("Search")
    }
}

@Preview(name = "EmptyTopAppBarLight")
@Preview(name = "EmptyTopAppBar Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun EmptyTopAppBarPreview() {
    ArtistAppTheme {
        EmptyTopAppBar()
    }
}