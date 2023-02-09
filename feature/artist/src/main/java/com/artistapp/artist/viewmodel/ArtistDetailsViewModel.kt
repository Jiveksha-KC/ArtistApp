package com.artistapp.artist.viewmodel

import androidx.compose.runtime.State
import com.artistapp.artist.state.ArtistDetailState

interface ArtistDetailsViewModel {

    val state: State<ArtistDetailState>

    fun onRetrySelected()
}