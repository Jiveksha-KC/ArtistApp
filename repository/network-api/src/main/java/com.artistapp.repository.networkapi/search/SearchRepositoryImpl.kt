package com.artistapp.repository.networkapi.search

import com.artistapp.model.search.SearchArtistResult
import com.artistapp.network.api.ApiService
import com.artistapp.repository.networkapi.network.NetworkApiCallDelegate
import com.artistapp.model.resource.Resource
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val networkApiCallDelegate: NetworkApiCallDelegate
) : SearchRepository, NetworkApiCallDelegate by networkApiCallDelegate {

    override suspend fun searchArtist(query: String): Resource<SearchArtistResult> {
        return executeApiCall { apiService.searchArtist(query) }
    }


}