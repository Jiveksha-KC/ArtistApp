package com.artistapp.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(ACCEPT_KEY, ACCEPT_KEY_VALUE)
            .addHeader(USER_AGENT, USER_AGENT_VALUE)
            .build()

        return chain.proceed(request)
    }

    private companion object {
        const val ACCEPT_KEY = "Accept"
        const val ACCEPT_KEY_VALUE = "application/json"
        const val USER_AGENT = "User-Agent"
        const val USER_AGENT_VALUE = "ArtistApp/1.0.0"
    }
}