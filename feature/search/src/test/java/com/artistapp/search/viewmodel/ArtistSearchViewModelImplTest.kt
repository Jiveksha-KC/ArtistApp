package com.artistapp.search.viewmodel

import com.artistapp.common.domain.UseCaseResult
import com.artistapp.model.search.SearchArtistResult
import com.artistapp.search.domain.GetArtistsBySearchUseCase
import com.artistapp.search.state.ArtistSearchUiState
import com.artistapp.testutils.unittest.stubs.SearchArtistResultStub
import com.artistapp.testutils.unittest.util.ViewModelTestBase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtistSearchViewModelImplTest : ViewModelTestBase() {

    private companion object {
        private const val SOME_QUERY = "Joe"
        private val SOME_SEARCH_RESULT = SearchArtistResultStub.new()
    }

    @MockK
    private lateinit var mockGetArtistsBySearchUseCase: GetArtistsBySearchUseCase

    private lateinit var subject: ArtistSearchViewModelImpl

    /**
     * SET UP
     */

    @Before
    fun setUp() {
        givenGetArtistBySearch(UseCaseResult.SuccessResult(SOME_SEARCH_RESULT))
        instantiateSubject()
    }

    /**
     * TESTS
     */

    @Test
    fun `WHEN instantiated - GIVEN getArtistBySearch SuccessResult, THEN state UI is Loading then Loaded`() {
        givenGetArtistBySearch(UseCaseResult.SuccessResult(SOME_SEARCH_RESULT))

        instantiateSubject(advanceUntilIdle = false)

        with(subject.state) {
            assertThat(value.ui).isEqualTo(ArtistSearchUiState.Loading)
            coroutineTestRule.runCurrent()
            assertThat(value.ui).isEqualTo(ArtistSearchUiState.Loaded(SOME_SEARCH_RESULT))
        }
    }

    @Test
    fun `WHEN instantiated - GIVEN getArtistBySearch NetworkErrorResult THEN state UI is Loading then LoadingNetworkError`() {
        givenGetArtistBySearch(UseCaseResult.NetworkErrorResult)

        instantiateSubject(advanceUntilIdle = false)

        with(subject.state) {
            assertThat(value.ui).isEqualTo(ArtistSearchUiState.Loading)
            coroutineTestRule.runCurrent()
            assertThat(value.ui).isEqualTo(ArtistSearchUiState.LoadingNetworkError)
        }
    }

    @Test
    fun `WHEN instantiated - GIVEN getArtistBySearch GenericErrorResult THEN state UI is Loading then LoadingGenericError`() {
        givenGetArtistBySearch(UseCaseResult.GenericErrorResult)

        instantiateSubject(advanceUntilIdle = false)

        with(subject.state) {
            assertThat(value.ui).isEqualTo(ArtistSearchUiState.Loading)
            coroutineTestRule.runCurrent()
            assertThat(value.ui).isEqualTo(ArtistSearchUiState.LoadingGenericError)
        }
    }

    @Test
    fun `onRetrySelected() - GIVEN getArtistBySearch SuccessResult, THEN state UI is Loading then Loaded`() {
        givenGetArtistBySearch(UseCaseResult.SuccessResult(SOME_SEARCH_RESULT))

        instantiateSubject(advanceUntilIdle = false)

        subject.onRetrySelected()

        with(subject.state) {
            assertThat(value.ui).isEqualTo(ArtistSearchUiState.Loading)
            coroutineTestRule.runCurrent()
            assertThat(value.ui).isEqualTo(ArtistSearchUiState.Loaded(SOME_SEARCH_RESULT))
        }
    }

    @Test
    fun `onRetrySelected() - GIVEN getArtistBySearch NetworkErrorResult THEN state UI is Loading then LoadingNetworkError`() {
        givenGetArtistBySearch(UseCaseResult.NetworkErrorResult)

        instantiateSubject(advanceUntilIdle = false)

        subject.onRetrySelected()

        with(subject.state) {
            assertThat(value.ui).isEqualTo(ArtistSearchUiState.Loading)
            coroutineTestRule.runCurrent()
            assertThat(value.ui).isEqualTo(ArtistSearchUiState.LoadingNetworkError)
        }
    }

    @Test
    fun `onRetrySelected() - GIVEN getArtistBySearch GenericErrorResult THEN state UI is Loading then LoadingGenericError`() {
        givenGetArtistBySearch(UseCaseResult.GenericErrorResult)

        instantiateSubject(advanceUntilIdle = false)

        subject.onRetrySelected()

        with(subject.state) {
            assertThat(value.ui).isEqualTo(ArtistSearchUiState.Loading)
            coroutineTestRule.runCurrent()
            assertThat(value.ui).isEqualTo(ArtistSearchUiState.LoadingGenericError)
        }
    }

    /**
     * GIVEN
     */
    private fun givenGetArtistBySearch(result: UseCaseResult<SearchArtistResult>) {
        coEvery { mockGetArtistsBySearchUseCase(SOME_QUERY) } returns result
    }

    /**
     * INSTANTIATE SUBJECT
     */
    private fun instantiateSubject(advanceUntilIdle: Boolean = true) {
        subject = ArtistSearchViewModelImpl(mockGetArtistsBySearchUseCase)
        if (advanceUntilIdle) {
            coroutineTestRule.runCurrent()
        }
    }
}
