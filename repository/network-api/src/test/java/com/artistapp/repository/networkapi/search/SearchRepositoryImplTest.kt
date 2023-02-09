package com.artistapp.repository.networkapi.search

import com.artistapp.model.resource.Resource
import com.artistapp.network.api.ApiService
import com.artistapp.testutils.unittest.stubs.SearchArtistResultStub
import com.google.common.truth.Truth.assertThat
import com.testapp.repository.networkapi.util.NetworkRepositoryTestBase
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchRepositoryImplTest : NetworkRepositoryTestBase<SearchRepositoryImpl>() {

    private companion object {
        private const val SOME_QUERY = "Joe"
        private val SOME_SEARCH_RESULT = SearchArtistResultStub.new()
    }

    @MockK
    private lateinit var mockApiService: ApiService

    /**
     * TESTS
     */
    @Test
    fun `getArtist() - GIVEN call success, THEN returns artist info`() {

        everyApiCallExecutionOf { mockApiService.searchArtist(SOME_QUERY) } returns SOME_SEARCH_RESULT

        val result = runBlocking { subject.searchArtist(SOME_QUERY) }

        assertThat(result).isEqualTo(Resource.Success(SOME_SEARCH_RESULT))
    }
}