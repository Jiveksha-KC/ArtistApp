package com.artistapp.artist.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artistapp.artist.constants.ArtistDetailScreenConstants.ARTIST_ID
import com.artistapp.artist.domain.GetArtistDetailUseCase
import com.artistapp.artist.state.ArtistDetailState
import com.artistapp.artist.state.ArtistDetailUiState
import com.artistapp.common.domain.UseCaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailsViewModelImpl @Inject constructor(
    savedStateHandle: SavedStateHandle, private val getArtistDetailUseCase: GetArtistDetailUseCase
) : ViewModel(), ArtistDetailsViewModel {

    private val artistId: String =
        savedStateHandle[ARTIST_ID] ?: throw IllegalArgumentException("Must provide $ARTIST_ID")

    override val state = mutableStateOf(ArtistDetailState(ArtistDetailUiState.Loading))

    init {
        loadArtistInfo(artistId)
    }

    override fun onRetrySelected() {
        state.updateUI(ArtistDetailUiState.Loading)
        loadArtistInfo(artistId)
    }

    private fun loadArtistInfo(artistId: String) {
        viewModelScope.launch {
            when (val result = getArtistDetailUseCase(artistId)) {
                is UseCaseResult.SuccessResult -> {
                    val artistDetail = result.value
                    if (artistDetail.albums.isNotEmpty()) {
                        state.updateUI(ArtistDetailUiState.Loaded(artistDetail = result.value))
                    } else {
                        state.updateUI(ArtistDetailUiState.EmptyAlbumList(artistDetail = result.value))
                    }
                }
                UseCaseResult.GenericErrorResult -> state.updateUI(ArtistDetailUiState.LoadingGenericError)
                UseCaseResult.NetworkErrorResult -> state.updateUI(ArtistDetailUiState.LoadingNetworkError)
            }
        }
    }

    private fun MutableState<ArtistDetailState>.updateUI(artist: ArtistDetailUiState) {
        value = value.copy(ui = artist)
    }
}