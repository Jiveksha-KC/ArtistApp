package com.artistapp.network.mapper.album

import com.artistapp.model.album.Album
import com.artistapp.network.model.album.ReleaseGroupResponse
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

internal object ReleaseGroupResponseMapper {

    @FromJson
    fun fromJson(response: ReleaseGroupResponse): List<Album> {
        with(response) {
            return response.releaseGroups.map {
                AlbumResponseMapper.fromJson(it)
            }
        }
    }

    @ToJson
    @Suppress("UNUSED_PARAMETER", "unused")
    fun toJson(writer: JsonWriter, value: List<Album>?) {
        throw UnsupportedOperationException()
    }
}