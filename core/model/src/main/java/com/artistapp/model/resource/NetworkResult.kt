package com.artistapp.model.resource

import com.artistapp.model.resource.Resource.Error
import com.artistapp.model.resource.Resource.Success

sealed class Resource<out T> {
    data class Success<T>(val value: T) : Resource<T>()
    data class Error(val type: ResourceErrorType) : Resource<Nothing>()
}

inline fun <R, T> Resource<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (exception: ResourceErrorType) -> R
): R {
    return when (this) {
        is Success -> onSuccess(value)
        is Error -> onFailure(type)
    }
}