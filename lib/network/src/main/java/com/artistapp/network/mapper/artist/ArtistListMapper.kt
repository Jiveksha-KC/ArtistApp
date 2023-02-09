package com.artistapp.network.mapper.artist

import com.artistapp.model.artist.Artist
import com.artistapp.network.model.artist.ArtistResponse
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

object ArtistListMapper {

    @FromJson
    fun fromJson(response: List<ArtistResponse>): List<Artist> {
        return response.map { ArtistResponseMapper.fromJson(it) }
    }

    @ToJson
    @Suppress("UNUSED_PARAMETER", "unused")
    fun toJson(writer: JsonWriter, value: Artist?) {
        throw UnsupportedOperationException()
    }

}