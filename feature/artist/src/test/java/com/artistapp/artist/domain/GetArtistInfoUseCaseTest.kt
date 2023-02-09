package com.artistapp.artist.domain

import com.artistapp.artist.domain.mapper.ArtistAlbumsRequestMapper
import com.artistapp.artist.domain.mapper.ArtistDetailMapper
import com.artistapp.artist.stub.ArtistDetailStub
import com.artistapp.common.domain.UseCaseResult
import com.artistapp.model.resource.Resource
import com.artistapp.model.resource.ResourceErrorType
import com.artistapp.repository.networkapi.artist.ArtistRepository
import com.artistapp.testutils.unittest.stubs.AlbumStub
import com.artistapp.testutils.unittest.stubs.ArtistAlbumRequestStub
import com.artistapp.testutils.unittest.stubs.ArtistStub
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetArtistInfoUseCaseTest {

    private companion object {
        private val SOME_ARTIST = ArtistStub.new()
        private val SOME_ARTIST_DETAIL = ArtistDetailStub.new()
        private val SOME_ALBUM_REQUEST = ArtistAlbumRequestStub.new()
        private val SOME_ALBUM_LIST = listOf(AlbumStub.new())
    }

    @get:Rule
    val mockKRule = MockKRule(this)

    @MockK
    private lateinit var mockArtistRepository: ArtistRepository

    @MockK
    private lateinit var mockArtistDetailMapper: ArtistDetailMapper

    @MockK
    private lateinit var mockArtistAlbumRequestMapper: ArtistAlbumsRequestMapper

    @InjectMockKs
    private lateinit var subject: GetArtistDetailUseCase

    @Before
    fun setUp() {
        coEvery { mockArtistRepository.getArtistInfo(SOME_ARTIST.id) } returns Resource.Success(
            SOME_ARTIST
        )

        coEvery { mockArtistRepository.getArtistAlbums(SOME_ALBUM_REQUEST) } returns Resource.Success(
            SOME_ALBUM_LIST
        )

        every {
            mockArtistDetailMapper.mapTo(SOME_ARTIST, SOME_ALBUM_LIST)
        } returns SOME_ARTIST_DETAIL

        every {
            mockArtistAlbumRequestMapper.mapTo(SOME_ARTIST.id)
        } returns SOME_ALBUM_REQUEST
    }

    @Test
    fun `invoke() - GIVEN Success, THEN SuccessResult`() {
        val result = runBlocking { subject(SOME_ARTIST.id) }

        assertThat(result).isEqualTo(UseCaseResult.SuccessResult(SOME_ARTIST_DETAIL))
    }

    @Test
    fun `invoke() - GIVEN Connection Error, THEN NetworkErrorResult`() {
        coEvery {
            mockArtistRepository.getArtistInfo(SOME_ARTIST.id)
        } returns Resource.Error(ResourceErrorType.Connection)

        val result = runBlocking { subject(SOME_ARTIST.id) }

        assertThat(result).isEqualTo(UseCaseResult.NetworkErrorResult)
    }

    @Test
    fun `invoke() - GIVEN Generic Error, THEN GenericErrorResult`() {
        coEvery {
            mockArtistRepository.getArtistInfo(SOME_ARTIST.id)
        } returns Resource.Error(ResourceErrorType.Generic)

        val result = runBlocking { subject(SOME_ARTIST.id) }

        assertThat(result).isEqualTo(UseCaseResult.GenericErrorResult)
    }

    @Test
    fun `invoke() - GIVEN getArtistAlbum returns Connection Error, THEN NetworkErrorResult`() {
        coEvery {
            mockArtistRepository.getArtistAlbums(SOME_ALBUM_REQUEST)
        } returns Resource.Error(ResourceErrorType.Connection)

        val result = runBlocking { subject(SOME_ARTIST.id) }

        assertThat(result).isEqualTo(UseCaseResult.NetworkErrorResult)
    }

    @Test
    fun `invoke() - GIVEN getArtistAlbum returns Generic Error, THEN GenericErrorResult`() {
        coEvery {
            mockArtistRepository.getArtistAlbums(SOME_ALBUM_REQUEST)
        } returns Resource.Error(ResourceErrorType.Generic)

        val result = runBlocking { subject(SOME_ARTIST.id) }

        assertThat(result).isEqualTo(UseCaseResult.GenericErrorResult)
    }
}