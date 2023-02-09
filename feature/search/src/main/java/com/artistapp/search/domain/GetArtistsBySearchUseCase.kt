package com.artistapp.search.domain

import com.artistapp.common.domain.UseCaseResult
import com.artistapp.model.resource.ResourceErrorType
import com.artistapp.model.resource.fold
import com.artistapp.model.search.SearchArtistResult
import com.artistapp.repository.networkapi.search.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetArtistsBySearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String): UseCaseResult<SearchArtistResult> =
        withContext(Dispatchers.Default) {
            searchRepository.searchArtist(query).fold(
                onSuccess = { UseCaseResult.SuccessResult(it) },
                onFailure = { errorType ->
                    when (errorType) {
                        is ResourceErrorType.Connection -> UseCaseResult.NetworkErrorResult
                        else -> UseCaseResult.GenericErrorResult
                    }
                }
            )
        }
}