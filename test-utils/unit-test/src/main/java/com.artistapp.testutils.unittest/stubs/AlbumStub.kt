package com.artistapp.testutils.unittest.stubs

import com.artistapp.model.album.Album

@Suppress("MemberVisibilityCanBePrivate")
object AlbumStub {

    const val ID = "29762c82-bb92-4acd-b1fb-09cc4da250d2"
    const val TITLE = "Ricky Martin"
    const val FIRST_RELEASE_DATE = "26 Oct, 1996"

    fun new(
        id: String = ID,
        title: String = TITLE,
        firstReleaseDate: String = FIRST_RELEASE_DATE
    ) = Album(
        id = id,
        title = title,
        firstReleaseDate = firstReleaseDate
    )
}