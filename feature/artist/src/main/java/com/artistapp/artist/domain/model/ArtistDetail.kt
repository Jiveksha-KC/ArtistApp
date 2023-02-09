package com.artistapp.artist.domain.model

import com.artistapp.model.album.Album
import com.artistapp.model.artist.Artist

data class ArtistDetail(
    val artist: Artist,
    val albums: List<Album>
)