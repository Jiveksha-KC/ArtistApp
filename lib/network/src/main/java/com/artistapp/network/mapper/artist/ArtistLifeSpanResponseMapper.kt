package com.artistapp.network.mapper.artist

import com.artistapp.model.artist.ArtistLifeSpan
import com.artistapp.network.model.artist.ArtistLifeSpanResponse
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

object ArtistLifeSpanResponseMapper {
    @FromJson
    fun fromJson(response: ArtistLifeSpanResponse): ArtistLifeSpan {
        with(response) {
            return ArtistLifeSpan(
                begin = begin ?: "",
                end = end ?: "",
                ended = ended ?: false
            )
        }
    }

    @ToJson
    @Suppress("UNUSED_PARAMETER", "unused")
    fun toJson(writer: JsonWriter, value: ArtistLifeSpan?) {
        throw UnsupportedOperationException()
    }
}