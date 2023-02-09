package com.artistapp.artist.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.artistapp.artist.constants.ArtistDetailScreenConstants.ARTIST_ID
import com.artistapp.artist.domain.GetArtistDetailUseCase
import com.artistapp.artist.domain.model.ArtistDetail
import com.artistapp.artist.state.ArtistDetailUiState
import com.artistapp.artist.stub.ArtistDetailStub
import com.artistapp.common.domain.UseCaseResult
import com.artistapp.testutils.unittest.stubs.ArtistStub
import com.artistapp.testutils.unittest.util.ViewModelTestBase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtistDetailsViewModelImplTest : ViewModelTestBase() {

    private companion object {
        private val SOME_ARTIST = ArtistStub.new()
        private val SOME_ARTIST_DETAIL = ArtistDetailStub.new()
    }

    @MockK
    private lateinit var mockSavedStateHandle: SavedStateHandle

    @MockK
    private lateinit var mockGetArtistDetailUseCase: GetArtistDetailUseCase

    private lateinit var subject: ArtistDetailsViewModelImpl

    /**
     * SET UP
     */

    @Before
    fun setUp() {
        every { mockSavedStateHandle.get<String>(ARTIST_ID) } returns SOME_ARTIST.id
        givenGetArtistInfo(UseCaseResult.SuccessResult(SOME_ARTIST_DETAIL))
        instantiateSubject()
    }

    /**
     * TESTS
     */

    @Test
    fun `WHEN instantiated - GIVEN getArtistInfo SuccessResult, THEN state UI is Loading then Loaded`() {
        givenGetArtistInfo(UseCaseResult.SuccessResult(SOME_ARTIST_DETAIL))

        instantiateSubject(advanceUntilIdle = false)

        with(subject.state) {
            assertThat(value.ui).isEqualTo(ArtistDetailUiState.Loading)
            coroutineTestRule.runCurrent()
            assertThat(value.ui).isEqualTo(ArtistDetailUiState.Loaded(SOME_ARTIST_DETAIL))
        }
    }

    @Test
    fun `WHEN instantiated - GIVEN getArtistInfo NetworkErrorResult THEN state UI is Loading then LoadingNetworkError`() {
        givenGetArtistInfo(UseCaseResult.NetworkErrorResult)

        instantiateSubject(advanceUntilIdle = false)

        with(subject.state) {
            assertThat(value.ui).isEqualTo(ArtistDetailUiState.Loading)
            coroutineTestRule.runCurrent()
            assertThat(value.ui).isEqualTo(ArtistDetailUiState.LoadingNetworkError)
        }
    }

    @Test
    fun `WHEN instantiated - GIVEN getArtistInfo GenericErrorResult THEN state UI is Loading then LoadingGenericError`() {
        givenGetArtistInfo(UseCaseResult.GenericErrorResult)

        instantiateSubject(advanceUntilIdle = false)

        with(subject.state) {
            assertThat(value.ui).isEqualTo(ArtistDetailUiState.Loading)
            coroutineTestRule.runCurrent()
            assertThat(value.ui).isEqualTo(ArtistDetailUiState.LoadingGenericError)
        }
    }

    @Test
    fun `onRetrySelected() - GIVEN getArtistInfo SuccessResult, THEN state UI is Loading then Loaded`() {
        givenGetArtistInfo(UseCaseResult.SuccessResult(SOME_ARTIST_DETAIL))

        subject.onRetrySelected()

        with(subject.state) {
            assertThat(value.ui).isEqualTo(ArtistDetailUiState.Loading)
            coroutineTestRule.runCurrent()
            assertThat(value.ui).isEqualTo(ArtistDetailUiState.Loaded(SOME_ARTIST_DETAIL))
        }
    }

    @Test
    fun `onRetrySelected() - GIVEN getArtistInfo NetworkErrorResult THEN state UI is Loading then LoadingNetworkError`() {
        givenGetArtistInfo(UseCaseResult.NetworkErrorResult)

        subject.onRetrySelected()

        with(subject.state) {
            assertThat(value.ui).isEqualTo(ArtistDetailUiState.Loading)
            coroutineTestRule.runCurrent()
            assertThat(value.ui).isEqualTo(ArtistDetailUiState.LoadingNetworkError)
        }
    }

    @Test
    fun `onRetrySelected() - GIVEN getFeedRoundUp GenericErrorResult THEN state UI is Loading then LoadingGenericError`() {
        givenGetArtistInfo(UseCaseResult.GenericErrorResult)

        subject.onRetrySelected()

        with(subject.state) {
            assertThat(value.ui).isEqualTo(ArtistDetailUiState.Loading)
            coroutineTestRule.runCurrent()
            assertThat(value.ui).isEqualTo(ArtistDetailUiState.LoadingGenericError)
        }
    }

    /**
     * GIVEN
     */
    private fun givenGetArtistInfo(result: UseCaseResult<ArtistDetail>) {
        coEvery { mockGetArtistDetailUseCase(SOME_ARTIST.id) } returns result
    }

    /**
     * INSTANTIATE SUBJECT
     */
    private fun instantiateSubject(advanceUntilIdle: Boolean = true) {
        subject = ArtistDetailsViewModelImpl(mockSavedStateHandle, mockGetArtistDetailUseCase)
        if (advanceUntilIdle) {
            coroutineTestRule.runCurrent()
        }
    }
}
