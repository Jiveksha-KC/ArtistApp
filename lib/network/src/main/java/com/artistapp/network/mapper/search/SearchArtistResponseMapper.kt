package com.artistapp.network.mapper.search

import com.artistapp.model.search.SearchArtistResult
import com.artistapp.network.mapper.artist.ArtistListMapper
import com.artistapp.network.model.search.ArtistSearchResponse
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

object SearchArtistResponseMapper {

    @FromJson
    fun fromJson(response: ArtistSearchResponse): SearchArtistResult {
        with(response) {
            return SearchArtistResult(
                artists = ArtistListMapper.fromJson(artists),
                count = count,
                created = created,
                offset = offset
            )
        }
    }

    @ToJson
    @Suppress("UNUSED_PARAMETER", "unused")
    fun toJson(writer: JsonWriter, value: SearchArtistResult?) {
        throw UnsupportedOperationException()
    }
}