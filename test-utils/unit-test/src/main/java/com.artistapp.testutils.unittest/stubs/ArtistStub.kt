package com.artistapp.testutils.unittest.stubs

import ArtistLifeSpanStub
import com.artistapp.model.artist.Artist
import com.artistapp.model.artist.ArtistLifeSpan

@Suppress("MemberVisibilityCanBePrivate")
object ArtistStub {

    const val COUNTRY = "US"
    const val GENDER = "MAlE"
    const val ID = "29762c82-bb92-4acd-b1fb-09cc4da250d2"
    val LIFE_SPAN = ArtistLifeSpanStub.new()
    const val NAME = "Ricky Martin"
    const val SORT_NAME = "Martin, Ricky"

    fun new(
        country: String = COUNTRY,
        gender: String = GENDER,
        id: String = ID,
        lifeSpan: ArtistLifeSpan = LIFE_SPAN,
        name: String = NAME,
        sortName: String = SORT_NAME
    ) =
        Artist(
            country = country,
            gender = gender,
            id = id,
            lifeSpan = lifeSpan,
            name = name,
            sortName = sortName
        )
}