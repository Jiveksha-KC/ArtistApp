package com.artistapp.network.model.album

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumResponse(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "primary-type") val primaryType: String,
    @Json(name = "primary-type-id") val primaryTypeId: String,
    @Json(name = "secondary-type-ids") val secondaryTypeIds: List<String>,
    @Json(name = "secondary-types") val secondaryTypes: List<String>,
    @Json(name = "first-release-date") val firstReleaseDate: String,
    @Json(name = "disambiguation") val disambiguation: String
)