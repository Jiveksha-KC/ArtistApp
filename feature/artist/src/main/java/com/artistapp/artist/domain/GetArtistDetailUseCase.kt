package com.artistapp.artist.domain

import com.artistapp.artist.domain.mapper.ArtistAlbumsRequestMapper
import com.artistapp.artist.domain.mapper.ArtistDetailMapper
import com.artistapp.artist.domain.model.ArtistDetail
import com.artistapp.common.domain.UseCaseResult
import com.artistapp.model.artist.Artist
import com.artistapp.model.resource.ResourceErrorType
import com.artistapp.model.resource.fold
import com.artistapp.repository.networkapi.artist.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetArtistDetailUseCase @Inject constructor(
    private val artistRepository: ArtistRepository,
    private val artistDetailMapper: ArtistDetailMapper,
    private val artistAlbumRequestMapper: ArtistAlbumsRequestMapper
) {
    suspend operator fun invoke(artistId: String): UseCaseResult<ArtistDetail> =
        withContext(Dispatchers.Default) {
            artistRepository.getArtistInfo(artistId).fold(
                onSuccess = { getArtistAlbum(it) },
                onFailure = { errorType ->
                    when (errorType) {
                        is ResourceErrorType.Connection -> UseCaseResult.NetworkErrorResult
                        else -> UseCaseResult.GenericErrorResult
                    }
                }
            )
        }

    private suspend fun getArtistAlbum(artist: Artist) =
        withContext(Dispatchers.Default) {
            artistRepository.getArtistAlbums(artistAlbumRequestMapper.mapTo(artist.id))
        }.fold(
            onSuccess = { UseCaseResult.SuccessResult((artistDetailMapper.mapTo(artist, it))) },
            onFailure = { errorType ->
                when (errorType) {
                    is ResourceErrorType.Connection -> UseCaseResult.NetworkErrorResult
                    else -> UseCaseResult.GenericErrorResult
                }
            }
        )
}