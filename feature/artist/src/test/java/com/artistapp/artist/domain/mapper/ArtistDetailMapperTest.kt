package com.artist.domain.mapper

import com.artistapp.artist.domain.mapper.ArtistDetailMapper
import com.artistapp.testutils.unittest.stubs.AlbumStub
import com.artistapp.testutils.unittest.stubs.ArtistStub
import com.google.common.truth.Truth.assertThat
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test

class ArtistDetailMapperTest {

    private companion object {
        private val SOME_ARTIST = ArtistStub.new()
        private val SOME_ALBUM_LIST = listOf(AlbumStub.new())
    }

    @get:Rule
    val mockKRule = MockKRule(this)

    @InjectMockKs
    private lateinit var subject: ArtistDetailMapper

    @Test
    fun `mapTo()- THEN return artist`() {
        val result = subject.mapTo(SOME_ARTIST, SOME_ALBUM_LIST)

        assertThat(result).isEqualTo(SOME_ARTIST)
    }
}