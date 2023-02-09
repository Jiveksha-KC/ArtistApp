package com.artistapp.injection

import com.artistapp.common.config.ConfigProvider
import com.artistapp.config.ConfigProviderImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Reusable
    fun provideConfigProvider(): ConfigProvider = ConfigProviderImpl
}