package com.artistapp.search.view

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artistapp.common.noop.NO_OP
import com.artistapp.model.artist.Artist
import com.artistapp.model.artist.ArtistLifeSpan
import com.artistapp.model.search.SearchArtistResult
import com.artistapp.resource.R
import com.artistapp.search.component.ArtistList
import com.artistapp.search.component.ArtistSearchView
import com.artistapp.search.state.ArtistSearchNavigation
import com.artistapp.search.state.ArtistSearchState
import com.artistapp.search.state.ArtistSearchUiState
import com.artistapp.search.viewmodel.ArtistSearchViewModel
import com.artistapp.ui.components.GenericErrorPane
import com.artistapp.ui.components.LogoTopAppBar
import com.artistapp.ui.components.NetworkErrorPane
import com.artistapp.ui.components.ShowLoader
import com.artistapp.ui.event.Observe
import com.artistapp.ui.theme.ArtistAppTheme
import com.artistapp.ui.theme.genericHorizontalContentPadding

@Composable
fun ArtistSearchScreen(
    viewModel: ArtistSearchViewModel,
    setTopLevelAppBar: @Composable (content: @Composable () -> Unit) -> Unit,
    navigateToArtistInfo: (artist: String) -> Unit
) {
    val state by viewModel.state

    setTopLevelAppBar { LogoTopAppBar(title = stringResource(id = R.string.search_title)) }

    val loadingContentDescription =
        stringResource(id = R.string.general_loading_label)

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ShowSearchView(
                modifier = Modifier.padding(
                    horizontal = genericHorizontalContentPadding, vertical = 16.dp
                ),
                viewModel::searchArtist
            )

            Spacer(modifier = Modifier.height(12.dp))

            Crossfade(targetState = state.ui) { ui ->
                when (ui) {
                    ArtistSearchUiState.Loading -> ShowLoader(
                        modifier = Modifier.clearAndSetSemantics {
                            contentDescription = loadingContentDescription
                        }
                    )
                    is ArtistSearchUiState.Loaded -> ShowArtistList(
                        modifier = Modifier.fillMaxSize(),
                        ui.searchResult,
                        viewModel::onArtistSelected
                    )
                    ArtistSearchUiState.LoadingGenericError -> GenericErrorPane(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(
                                horizontal = genericHorizontalContentPadding, vertical = 16.dp
                            ), retryAction = viewModel::onRetrySelected
                    )
                    ArtistSearchUiState.LoadingNetworkError -> NetworkErrorPane(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(
                                horizontal = genericHorizontalContentPadding, vertical = 16.dp
                            ), retryAction = viewModel::onRetrySelected
                    )
                }
            }
        }
    }

    state.navigation.Observe(
        handler = { navigation ->
            when (navigation) {
                is ArtistSearchNavigation.NavigateToArtistInfo ->
                    navigateToArtistInfo(navigation.artistId)
            }
        },
        eventConsumed = viewModel::onNavigationConsumed
    )
}

@Composable
fun ShowSearchView(
    modifier: Modifier,
    onSearchArtist: (String) -> Unit
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    ArtistSearchView(modifier, state = textState, onSearch = onSearchArtist)
}

@Composable
fun ShowArtistList(
    modifier: Modifier,
    searchArtistResult: SearchArtistResult,
    onArtistSelected: (String) -> Unit
) {
    ArtistList(modifier, searchArtistResult, onArtistSelected)
}

/**
 * PLACEHOLDER DATA
 */

private val placeholderSearchResult =
    SearchArtistResult(
        artists = listOf(
            Artist(
                country = "US",
                gender = "Male",
                id = "29762c82-bb92-4acd-b1fb-09cc4da250d2",
                lifeSpan = ArtistLifeSpan(
                    begin = "1993-10-26",
                    end = "1993-10-26",
                    ended = false
                ),
                name = "Ricky Martin",
                sortName = "Martin, Ricky"
            )
        ),
        count = 25,
        created = "1993-10-26",
        offset = 0
    )

/**
 * PREVIEW
 */

@Preview(name = "SearchScreenLoaded Light")
@Preview(name = "SearchScreenLoaded Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewSearchScreenLoaded() {
    ArtistAppTheme {
        ArtistSearchScreen(
            viewModel = PreviewViewModel(ArtistSearchUiState.Loaded(placeholderSearchResult)),
            setTopLevelAppBar = {},
            navigateToArtistInfo = {}
        )
    }
}

@Preview(name = "SearchScreenLoadingGenericError Light")
@Preview(
    name = "SearchScreenLoadingGenericError Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewSearchScreenGenericError() {
    ArtistAppTheme {
        ArtistSearchScreen(
            viewModel = PreviewViewModel(ArtistSearchUiState.LoadingGenericError),
            setTopLevelAppBar = {},
            navigateToArtistInfo = {}
        )
    }
}

@Preview(name = "SearchScreenLoading Light")
@Preview(name = "SearchScreenLoading Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewSearchScreenLoading() {
    ArtistAppTheme {
        ArtistSearchScreen(
            viewModel = PreviewViewModel(ArtistSearchUiState.Loading),
            setTopLevelAppBar = {},
            navigateToArtistInfo = {}
        )
    }
}

private class PreviewViewModel(
    ui: ArtistSearchUiState
) : ArtistSearchViewModel {

    override val state = mutableStateOf(ArtistSearchState(ui))

    override fun onRetrySelected() = NO_OP

    override fun onArtistSelected(artistId: String) = NO_OP

    override fun searchArtist(query: String) = NO_OP

    override fun onNavigationConsumed(navigationId: String) = NO_OP
}




