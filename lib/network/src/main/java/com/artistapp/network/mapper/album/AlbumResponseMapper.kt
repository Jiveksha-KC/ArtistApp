package com.artistapp.network.mapper.album

import com.artistapp.common.extensions.toDisplayFormat
import com.artistapp.model.album.Album
import com.artistapp.network.model.album.AlbumResponse
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

internal object AlbumResponseMapper {

    @FromJson
    fun fromJson(response: AlbumResponse): Album {
        with(response) {
            return Album(
                id = id,
                title = title,
                firstReleaseDate = firstReleaseDate.toDisplayFormat()
            )
        }
    }

    @ToJson
    @Suppress("UNUSED_PARAMETER", "unused")
    fun toJson(writer: JsonWriter, value: Album?) {
        throw UnsupportedOperationException()
    }
}