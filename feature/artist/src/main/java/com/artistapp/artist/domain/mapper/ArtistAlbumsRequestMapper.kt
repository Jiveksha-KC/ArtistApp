package com.artistapp.artist.domain.mapper

import com.artistapp.model.artist.request.ArtistAlbumRequest
import javax.inject.Inject

class ArtistAlbumsRequestMapper @Inject constructor() {

    private val requestGroupType = "album"

    fun mapTo(artistId: String) = ArtistAlbumRequest(
        artistId = artistId,
        type = requestGroupType
    )
}