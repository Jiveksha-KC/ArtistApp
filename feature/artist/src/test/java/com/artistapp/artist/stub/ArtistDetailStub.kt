package com.artistapp.artist.stub

import com.artistapp.artist.domain.model.ArtistDetail
import com.artistapp.model.album.Album
import com.artistapp.model.artist.Artist
import com.artistapp.testutils.unittest.stubs.AlbumStub
import com.artistapp.testutils.unittest.stubs.ArtistStub

@Suppress("MemberVisibilityCanBePrivate")
object ArtistDetailStub {

    val ARTIST = ArtistStub.new()
    val ALBUMS = listOf(AlbumStub.new())

    fun new(
        artist: Artist = ARTIST,
        albums: List<Album> = ALBUMS
    ) =
        ArtistDetail(
            artist,
            albums
        )
}