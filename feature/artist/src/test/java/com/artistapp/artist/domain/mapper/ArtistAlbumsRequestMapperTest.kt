package com.artistapp.artist.domain.mapper

import com.artistapp.testutils.unittest.stubs.ArtistAlbumRequestStub
import com.artistapp.testutils.unittest.stubs.ArtistStub
import com.google.common.truth.Truth.assertThat
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test

class ArtistAlbumsRequestMapperTest {

    private companion object {
        private val SOME_ARTIST = ArtistStub.new()
        private val SOME_ALBUM_REQUEST = ArtistAlbumRequestStub.new()
    }

    @get:Rule
    val mockKRule = MockKRule(this)

    @InjectMockKs
    private lateinit var subject: ArtistAlbumsRequestMapper

    @Test
    fun `mapTo()- THEN return artist`() {
        val result = subject.mapTo(SOME_ARTIST.id)

        assertThat(result).isEqualTo(SOME_ALBUM_REQUEST)
    }
}