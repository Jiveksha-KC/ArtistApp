package com.artistapp.network.model.artist

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistAreaResponse(
    @Json(name = "disambiguation") val disambiguation: String?,
    @Json(name = "id") val id: String,
    @Json(name = "iso-3166-1-codes") val isoCodes: List<String>?,
    @Json(name = "name") val name: String,
    @Json(name = "sort-name") val sortName: String,
    @Json(name = "type") val type: Any?,
    @Json(name = "type-id") val typeId: Any?
)