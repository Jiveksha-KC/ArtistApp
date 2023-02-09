package com.artistapp.network.model.artist

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistBeginAreaResponse(
    @Json(name = "disambiguation") val disambiguation: String?,
    @Json(name = "type-id") val typeId: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "sort-name") val sortName: String?,
    @Json(name = "id") val id: String?,
    @Json(name = "type") val type: String?
)