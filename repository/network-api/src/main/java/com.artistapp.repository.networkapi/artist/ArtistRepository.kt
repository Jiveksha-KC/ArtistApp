package com.artistapp.repository.networkapi.artist

import com.artistapp.model.album.Album
import com.artistapp.model.artist.Artist
import com.artistapp.model.artist.request.ArtistAlbumRequest
import com.artistapp.model.resource.Resource

interface ArtistRepository {

    suspend fun getArtistInfo(artistId: String): Resource<Artist>

    suspend fun getArtistAlbums(artistAlbumRequest: ArtistAlbumRequest): Resource<List<Album>>
}