package com.artistapp.testutils.unittest.stubs

import com.artistapp.model.artist.request.ArtistAlbumRequest

@Suppress("MemberVisibilityCanBePrivate")
object ArtistAlbumRequestStub {

    const val ARTIST_ID = "29762c82-bb92-4acd-b1fb-09cc4da250d2"
    const val TYPE = "album"

    fun new(
        artistId: String = ARTIST_ID,
        type: String = TYPE
    ) = ArtistAlbumRequest(
        artistId = artistId,
        type = type
    )
}