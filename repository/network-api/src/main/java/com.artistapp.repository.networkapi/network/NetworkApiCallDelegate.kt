package com.artistapp.repository.networkapi.network

import com.squareup.moshi.JsonDataException
import com.artistapp.model.resource.Resource
import com.artistapp.model.resource.ResourceErrorType
import com.artistapp.model.resource.isConnectionException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

interface NetworkApiCallDelegate {
    suspend fun <T : Any> executeApiCall(apiCall: suspend () -> T): Resource<T>
}

internal class NetworkApiCallDelegateImpl @Inject constructor() : NetworkApiCallDelegate {

    override suspend fun <T : Any> executeApiCall(apiCall: suspend () -> T): Resource<T> =
        withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall())
            } catch (expected: Throwable) {
                val errorType = getErrorType(expected)
                Resource.Error(errorType)
            }
        }

    private fun getErrorType(throwable: Throwable): ResourceErrorType {
        return when {
            throwable is CancellationException -> throw throwable
            throwable.isConnectionException() -> {
                ResourceErrorType.Connection
            }
            throwable is JsonDataException -> {
                ResourceErrorType.Generic
            }
            throwable is HttpException -> {
                ResourceErrorType.HttpError(throwable.code())
            }
            else -> {
                ResourceErrorType.Generic
            }
        }
    }
}