package com.artistapp.model.search

import com.artistapp.model.artist.Artist

data class SearchArtistResult(
    val artists: List<Artist>,
    val count: Int,
    val created: String,
    val offset: Int
)