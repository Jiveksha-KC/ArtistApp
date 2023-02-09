package com.artistapp.network.model.artist

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistResponse(
    @Json(name = "area") val area: ArtistAreaResponse?,
    @Json(name = "country") val country: String?,
    @Json(name = "disambiguation") val disambiguation: String?,
    @Json(name = "gender") val gender: String?,
    @Json(name = "gender-id") val genderId: String?,
    @Json(name = "begin_area") val beginArea: ArtistBeginAreaResponse?,
    @Json(name = "id") val id: String,
    @Json(name = "ipis") val ipis: List<String>?,
    @Json(name = "isnis") val isnis: List<String>?,
    @Json(name = "life-span") val lifeSpan: ArtistLifeSpanResponse,
    @Json(name = "name") val name: String,
    @Json(name = "sort-name") val sortName: String,
    @Json(name = "type") val type: String?,
    @Json(name = "type-id") val typeId: String?
)