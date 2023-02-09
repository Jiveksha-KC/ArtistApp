package com.artistapp.model.artist

data class Artist(
    val country: String,
    val gender: String,
    val id: String,
    val lifeSpan: ArtistLifeSpan,
    val name: String,
    val sortName: String
)