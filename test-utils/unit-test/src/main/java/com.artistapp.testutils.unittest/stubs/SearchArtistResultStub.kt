package com.artistapp.testutils.unittest.stubs

import com.artistapp.model.artist.Artist
import com.artistapp.model.search.SearchArtistResult

@Suppress("MemberVisibilityCanBePrivate")
object SearchArtistResultStub {

    val ARTISTS = listOf(ArtistStub.new())
    const val COUNT = 25
    const val CREATED = "1993-10-26"
    const val OFFSET = 0

    fun new(
        artists: List<Artist> = ARTISTS,
        count: Int = COUNT,
        created: String = CREATED,
        offset: Int = OFFSET
    ) = SearchArtistResult(
        artists = artists,
        count = count,
        created = created,
        offset = offset
    )
}