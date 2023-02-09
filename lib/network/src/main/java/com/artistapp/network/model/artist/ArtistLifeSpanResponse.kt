package com.artistapp.network.model.artist

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistLifeSpanResponse(
    @Json(name = "begin")val begin: String?,
    @Json(name = "end")val end: String?,
    @Json(name = "ended")val ended: Boolean?
)