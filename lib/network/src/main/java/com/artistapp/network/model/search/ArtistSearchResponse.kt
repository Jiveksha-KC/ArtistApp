package com.artistapp.network.model.search

import com.artistapp.network.model.artist.ArtistResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistSearchResponse(
    @Json(name = "created") val created: String,
    @Json(name = "count") val count: Int,
    @Json(name = "offset") val offset: Int,
    @Json(name = "artists") val artists: List<ArtistResponse>
)