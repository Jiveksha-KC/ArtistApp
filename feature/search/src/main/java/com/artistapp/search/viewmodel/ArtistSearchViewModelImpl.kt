package com.artistapp.search.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artistapp.common.domain.UseCaseResult
import com.artistapp.search.config.SearchConfig.DEFAULT_QUERY
import com.artistapp.search.domain.GetArtistsBySearchUseCase
import com.artistapp.search.state.ArtistSearchNavigation
import com.artistapp.search.state.ArtistSearchState
import com.artistapp.search.state.ArtistSearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistSearchViewModelImpl @Inject constructor(
    private val getArtistsBySearchUseCase: GetArtistsBySearchUseCase
) : ViewModel(), ArtistSearchViewModel {

    override val state = mutableStateOf(ArtistSearchState(ArtistSearchUiState.Loading))

    init {
        searchArtist(DEFAULT_QUERY)
    }

    override fun onRetrySelected() {
        state.updateUI(ArtistSearchUiState.Loading)
    }

    override fun onArtistSelected(artistId: String) {
        state.updateNavigation(
            state.value.navigation + ArtistSearchNavigation.NavigateToArtistInfo(
                artistId
            )
        )
    }

    override fun onNavigationConsumed(navigationId: String) {
        state.updateNavigation(state.value.navigation.filterNot { it.id == navigationId })
    }

    override fun searchArtist(query: String) {
        state.updateUI(ArtistSearchUiState.Loading)
        viewModelScope.launch {
            when (val result = getArtistsBySearchUseCase(query)) {
                is UseCaseResult.SuccessResult -> state.updateUI(
                    ArtistSearchUiState.Loaded(
                        searchResult = result.value
                    )
                )
                UseCaseResult.GenericErrorResult -> state.updateUI(ArtistSearchUiState.LoadingGenericError)
                UseCaseResult.NetworkErrorResult -> state.updateUI(ArtistSearchUiState.LoadingNetworkError)
            }
        }
    }

    private fun MutableState<ArtistSearchState>.updateUI(artist: ArtistSearchUiState) {
        value = value.copy(ui = artist)
    }

    private fun MutableState<ArtistSearchState>.updateNavigation(newNavigation: List<ArtistSearchNavigation>) {
        value = value.copy(navigation = newNavigation)
    }
}