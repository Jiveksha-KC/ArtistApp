package com.artistapp.repository.networkapi.artist

import com.artistapp.model.resource.Resource
import com.artistapp.network.api.ApiService
import com.artistapp.testutils.unittest.stubs.AlbumStub
import com.artistapp.testutils.unittest.stubs.ArtistAlbumRequestStub
import com.artistapp.testutils.unittest.stubs.ArtistStub
import com.google.common.truth.Truth.assertThat
import com.testapp.repository.networkapi.util.NetworkRepositoryTestBase
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ArtistRepositoryImplTest : NetworkRepositoryTestBase<ArtistRepositoryImpl>() {

    private companion object {
        private val SOME_ARTIST = ArtistStub.new()
        private val SOME_ARTIST_ALBUM_REQUEST = ArtistAlbumRequestStub.new()
        private val SOME_ALBUM_LIST = listOf(AlbumStub.new())
    }

    @MockK
    private lateinit var mockApiService: ApiService

    /**
     * TESTS
     */
    @Test
    fun `getArtist() - GIVEN call success, THEN returns artist info`() {

        everyApiCallExecutionOf { mockApiService.getArtist(SOME_ARTIST.id) } returns SOME_ARTIST

        val result = runBlocking { subject.getArtistInfo(SOME_ARTIST.id) }

        assertThat(result).isEqualTo(Resource.Success(SOME_ARTIST))
    }

    @Test
    fun `getAlbums() - GIVEN call success, THEN returns albums`() {

        everyApiCallExecutionOf {
            mockApiService.getAlbums(
                SOME_ARTIST_ALBUM_REQUEST.artistId,
                SOME_ARTIST_ALBUM_REQUEST.type
            )
        } returns SOME_ALBUM_LIST

        val result = runBlocking { subject.getArtistAlbums(SOME_ARTIST_ALBUM_REQUEST) }

        assertThat(result).isEqualTo(Resource.Success(SOME_ALBUM_LIST))
    }
}