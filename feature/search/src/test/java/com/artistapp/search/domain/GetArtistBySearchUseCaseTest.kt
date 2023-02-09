package com.artistapp.search.domain

import com.artistapp.common.domain.UseCaseResult
import com.artistapp.model.resource.Resource
import com.artistapp.model.resource.ResourceErrorType
import com.artistapp.repository.networkapi.search.SearchRepository
import com.artistapp.testutils.unittest.stubs.ArtistStub
import com.artistapp.testutils.unittest.stubs.SearchArtistResultStub
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class GetArtistBySearchUseCaseTest {

    private companion object {
        private const val SOME_QUERY = "Joe"
        private val SOME_ARTIST_RESULT = SearchArtistResultStub.new()
    }

    @get:Rule
    val mockKRule = MockKRule(this)

    @MockK
    private lateinit var mockSearchRepository: SearchRepository

    @InjectMockKs
    private lateinit var subject: GetArtistsBySearchUseCase

    @Test
    fun `invoke() - GIVEN Success, THEN SuccessResult`() {
        coEvery { mockSearchRepository.searchArtist(SOME_QUERY) } returns Resource.Success(
            SOME_ARTIST_RESULT
        )

        val result = runBlocking { subject(SOME_QUERY) }

        Truth.assertThat(result).isEqualTo(UseCaseResult.SuccessResult(SOME_ARTIST_RESULT))
    }

    @Test
    fun `invoke() - GIVEN Connection Error, THEN NetworkErrorResult`() {
        coEvery {
            mockSearchRepository.searchArtist(SOME_QUERY)
        } returns Resource.Error(ResourceErrorType.Connection)

        val result = runBlocking { subject(SOME_QUERY) }

        Truth.assertThat(result).isEqualTo(UseCaseResult.NetworkErrorResult)
    }

    @Test
    fun `invoke() - GIVEN Generic Error, THEN GenericErrorResult`() {
        coEvery {
            mockSearchRepository.searchArtist(SOME_QUERY)
        } returns Resource.Error(ResourceErrorType.Generic)

        val result = runBlocking { subject(SOME_QUERY) }

        Truth.assertThat(result).isEqualTo(UseCaseResult.GenericErrorResult)
    }
}