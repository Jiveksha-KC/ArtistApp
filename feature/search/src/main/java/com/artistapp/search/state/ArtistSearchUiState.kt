package com.artistapp.search.state

import com.artistapp.model.search.SearchArtistResult

sealed class ArtistSearchUiState {
    object Loading : ArtistSearchUiState()
    data class Loaded(val searchResult: SearchArtistResult) : ArtistSearchUiState()
    object LoadingGenericError : ArtistSearchUiState()
    object LoadingNetworkError : ArtistSearchUiState()
}