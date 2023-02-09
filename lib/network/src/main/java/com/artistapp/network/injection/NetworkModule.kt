package com.artistapp.network.injection

import com.artistapp.common.config.ConfigProvider
import com.artistapp.network.api.ApiService
import com.artistapp.network.config.MoshiAdapterConfig
import com.artistapp.network.interceptor.HeaderInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(
        retrofitBuilder: Retrofit.Builder
    ): ApiService {
        return retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        configProvider: ConfigProvider,
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(configProvider.apiBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor
    ) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(configProvider: ConfigProvider) =
        HttpLoggingInterceptor().apply { level = if (configProvider.isDebug) BODY else NONE }

    @Provides
    @Singleton
    fun provideMoshiFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideMoshiConfig(): Moshi = MoshiAdapterConfig.getMoshiConfig()
}