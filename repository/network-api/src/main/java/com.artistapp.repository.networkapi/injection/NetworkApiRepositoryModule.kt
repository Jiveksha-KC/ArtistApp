package com.artistapp.repository.networkapi.injection

import com.artistapp.repository.networkapi.artist.ArtistRepository
import com.artistapp.repository.networkapi.artist.ArtistRepositoryImpl
import com.artistapp.repository.networkapi.network.NetworkApiCallDelegate
import com.artistapp.repository.networkapi.network.NetworkApiCallDelegateImpl
import com.artistapp.repository.networkapi.search.SearchRepository
import com.artistapp.repository.networkapi.search.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkApiRepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindNetworkApiCallDelegate(
        networkApiExecutorImpl: NetworkApiCallDelegateImpl
    ): NetworkApiCallDelegate

    @Binds
    @Singleton
    abstract fun bindArtistRepository(
        artistRepositoryImpl: ArtistRepositoryImpl
    ): ArtistRepository

    @Binds
    @Singleton
    abstract fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository
}