package com.artistapp.repository.networkapi.search

import com.artistapp.model.search.SearchArtistResult
import com.artistapp.model.resource.Resource

interface SearchRepository {
    suspend fun searchArtist(query: String): Resource<SearchArtistResult>
}