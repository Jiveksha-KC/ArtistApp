package com.artistapp.search.viewmodel

import androidx.compose.runtime.State
import com.artistapp.search.state.ArtistSearchState

interface ArtistSearchViewModel {

    val state: State<ArtistSearchState>

    fun onRetrySelected()

    fun onArtistSelected(artistId: String)

    fun searchArtist(query: String)

    fun onNavigationConsumed(navigationId: String)
}