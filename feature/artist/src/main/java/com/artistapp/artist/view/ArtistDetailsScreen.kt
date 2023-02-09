package com.artistapp.artist.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artistapp.artist.component.AlbumItem
import com.artistapp.artist.component.ArtistDetailsHeader
import com.artistapp.artist.domain.model.ArtistDetail
import com.artistapp.artist.state.ArtistDetailState
import com.artistapp.artist.state.ArtistDetailUiState
import com.artistapp.artist.viewmodel.ArtistDetailsViewModel
import com.artistapp.common.noop.NO_OP
import com.artistapp.model.album.Album
import com.artistapp.model.artist.Artist
import com.artistapp.model.artist.ArtistLifeSpan
import com.artistapp.resource.R
import com.artistapp.ui.components.GenericErrorPane
import com.artistapp.ui.components.LogoTopAppBar
import com.artistapp.ui.components.NetworkErrorPane
import com.artistapp.ui.theme.ArtistAppTheme
import com.artistapp.ui.theme.genericHorizontalContentPadding

@Composable
fun ArtistDetailsScreen(
    viewModel: ArtistDetailsViewModel,
    setTopLevelAppBar: @Composable (content: @Composable () -> Unit) -> Unit
) {
    val state by viewModel.state

    setTopLevelAppBar { LogoTopAppBar(title = stringResource(id = R.string.artist_title)) }

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        val ui = state.ui

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .setListAccessibilityModifier(ui is ArtistDetailUiState.Loading),
            contentPadding = PaddingValues(
                vertical = 12.dp, horizontal = genericHorizontalContentPadding
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            when (ui) {
                is ArtistDetailUiState.Loading -> {
                    loading()
                }
                is ArtistDetailUiState.Loaded -> {
                    loaded(artistDetail = ui.artistDetail)
                }
                is ArtistDetailUiState.EmptyAlbumList -> {
                    emptyAlbumList(artistDetail = ui.artistDetail)
                }
                is ArtistDetailUiState.LoadingGenericError -> {
                    item {
                        GenericErrorPane(retryAction = viewModel::onRetrySelected)
                    }
                }
                is ArtistDetailUiState.LoadingNetworkError -> {
                    item {
                        NetworkErrorPane(retryAction = viewModel::onRetrySelected)
                    }
                }
            }
        }
    }
}

private fun LazyListScope.loading() {
    content(
        isLoading = true,
        artistDetail = placeHolderArtist
    )
}

private fun LazyListScope.loaded(
    artistDetail: ArtistDetail
) {
    content(
        isLoading = false,
        artistDetail = artistDetail,
    )
}

private fun LazyListScope.emptyAlbumList(
    artistDetail: ArtistDetail
) {
    item { ArtistDetailsHeader(artist = artistDetail.artist) }
    item {
        Text(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.subtitle1,
            fontStyle = FontStyle.Italic,
            text = stringResource(id = R.string.artist_no_albums)
        )
    }
}

private fun LazyListScope.content(
    isLoading: Boolean,
    artistDetail: ArtistDetail
) {
    item { ArtistDetailsHeader(artist = artistDetail.artist) }
    items(items = artistDetail.albums, key = { it.id }) {
        AlbumItem(
            isLoading = isLoading, album = it
        )
    }
}

private fun Modifier.setListAccessibilityModifier(isLoading: Boolean): Modifier = composed {
    if (isLoading) {
        val loadingContentDescription = stringResource(id = R.string.general_loading_label)
        clearAndSetSemantics { contentDescription = loadingContentDescription }
    } else this
}

private val placeHolderArtist = ArtistDetail(
    artist = Artist(
        id = "1",
        name = "FirstName",
        gender = "Male",
        lifeSpan = ArtistLifeSpan(
            "1956-07-15", "", false
        ),
        country = "USA",
        sortName = "firstName, LastName"
    ),
    albums = listOf(
        Album(
            id = "1",
            title = "ALbum",
            firstReleaseDate = "2022-07-15"
        )
    )
)

/**
 * PREVIEW
 */

@Preview(name = "ArtistDetailsScreenLoaded Light")
@Preview(name = "ArtistDetailsScreenLoaded Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewSearchScreenLoaded() {
    ArtistAppTheme {
        ArtistDetailsScreen(viewModel = PreviewViewModel(
            ArtistDetailUiState.Loaded(
                placeHolderArtist
            )
        ),
            setTopLevelAppBar = {})
    }
}

@Preview(name = "ArtistDetailsScreenLoadingGenericError Light")
@Preview(
    name = "ArtistDetailsScreenLoadingGenericError Dark", uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewSearchScreenGenericError() {
    ArtistAppTheme {
        ArtistDetailsScreen(viewModel = PreviewViewModel(ArtistDetailUiState.LoadingGenericError),
            setTopLevelAppBar = {})
    }
}

@Preview(name = "ArtistDetailsScreenLoading Light")
@Preview(name = "ArtistDetailsScreenLoading Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewSearchScreenLoading() {
    ArtistAppTheme {
        ArtistDetailsScreen(viewModel = PreviewViewModel(ArtistDetailUiState.Loading),
            setTopLevelAppBar = {})
    }
}

private class PreviewViewModel(
    ui: ArtistDetailUiState
) : ArtistDetailsViewModel {

    override val state = mutableStateOf(ArtistDetailState(ui))

    override fun onRetrySelected() = NO_OP
}






