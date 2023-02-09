package com.artistapp.network.config

import com.artistapp.network.mapper.album.AlbumResponseMapper
import com.artistapp.network.mapper.album.ReleaseGroupResponseMapper
import com.artistapp.network.mapper.artist.ArtistResponseMapper
import com.artistapp.network.mapper.search.SearchArtistResponseMapper
import com.squareup.moshi.Moshi

/**
 * Internal function that provides Moshi setup with all required mappers for use in both the
 * Network modules and unit tests.
 */
object MoshiAdapterConfig {
    fun getMoshiConfig(): Moshi =
        Moshi.Builder()
            .add(AlbumResponseMapper)
            .add(ReleaseGroupResponseMapper)
            .add(ArtistResponseMapper)
            .add(SearchArtistResponseMapper)
            .build()
}
