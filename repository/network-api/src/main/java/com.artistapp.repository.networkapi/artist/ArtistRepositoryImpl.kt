package com.artistapp.repository.networkapi.artist

import com.artistapp.model.album.Album
import com.artistapp.model.artist.Artist
import com.artistapp.model.artist.request.ArtistAlbumRequest
import com.artistapp.model.resource.Resource
import com.artistapp.network.api.ApiService
import com.artistapp.repository.networkapi.network.NetworkApiCallDelegate
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val networkApiCallDelegate: NetworkApiCallDelegate
) : ArtistRepository, NetworkApiCallDelegate by networkApiCallDelegate {

    override suspend fun getArtistInfo(artistId: String): Resource<Artist> {
        return executeApiCall { apiService.getArtist(artistId) }
    }

    override suspend fun getArtistAlbums(artistAlbumRequest: ArtistAlbumRequest): Resource<List<Album>> {
        return executeApiCall {
            apiService.getAlbums(
                artistAlbumRequest.artistId,
                artistAlbumRequest.type
            )
        }
    }
}