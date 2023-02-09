package com.artistapp.search.state

import com.artistapp.ui.event.OneShotEvent

sealed class ArtistSearchNavigation : OneShotEvent() {
    data class NavigateToArtistInfo(val artistId: String) :
        ArtistSearchNavigation()
}
