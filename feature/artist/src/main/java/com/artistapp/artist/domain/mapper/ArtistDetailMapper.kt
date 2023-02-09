package com.artistapp.artist.domain.mapper

import com.artistapp.artist.domain.model.ArtistDetail
import com.artistapp.model.album.Album
import com.artistapp.model.artist.Artist
import javax.inject.Inject

class ArtistDetailMapper @Inject constructor() {

    fun mapTo(artist: Artist, artistAlbums: List<Album>) = ArtistDetail(
        artist = artist,
        albums = artistAlbums
    )
}