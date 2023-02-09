package com.artistapp.network.model.album

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReleaseGroupResponse(
    @Json(name = "release-groups") val releaseGroups: List<AlbumResponse>
)