package com.artistapp.network.mapper.artist

import com.artistapp.model.artist.Artist
import com.artistapp.network.model.artist.ArtistResponse
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

object ArtistResponseMapper {

    @FromJson
    fun fromJson(response: ArtistResponse): Artist {
        with(response) {
            return Artist(
                country = country ?: "",
                gender = gender ?: "Unknown",
                id = id,
                lifeSpan = ArtistLifeSpanResponseMapper.fromJson(lifeSpan),
                name = name,
                sortName = name
            )
        }
    }

    @ToJson
    @Suppress("UNUSED_PARAMETER", "unused")
    fun toJson(writer: JsonWriter, value: Artist?) {
        throw UnsupportedOperationException()
    }
}