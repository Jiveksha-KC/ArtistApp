package com.artistapp.artist.state

import com.artistapp.artist.domain.model.ArtistDetail

sealed class ArtistDetailUiState {
    object Loading : ArtistDetailUiState()
    data class Loaded(val artistDetail: ArtistDetail) : ArtistDetailUiState()
    data class EmptyAlbumList(val artistDetail: ArtistDetail) : ArtistDetailUiState()
    object LoadingGenericError : ArtistDetailUiState()
    object LoadingNetworkError : ArtistDetailUiState()
}