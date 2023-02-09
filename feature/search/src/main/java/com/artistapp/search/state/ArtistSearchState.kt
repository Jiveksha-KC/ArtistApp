package com.artistapp.search.state

data class ArtistSearchState(
    val ui: ArtistSearchUiState,
    val navigation: List<ArtistSearchNavigation> = emptyList()
)