package com.artistapp.network.api

import com.artistapp.model.album.Album
import com.artistapp.model.artist.Artist
import com.artistapp.model.search.SearchArtistResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("artist")
    suspend fun searchArtist(
        @Query("query") searchQuery: String,
    ): SearchArtistResult

    @GET("artist/{artistId}")
    suspend fun getArtist(
        @Path("artistId") artistId: String
    ): Artist

    @GET("release-group")
    suspend fun getAlbums(
        @Query("artist") artistId: String,
        @Query("type") type: String
    ): List<Album>
}