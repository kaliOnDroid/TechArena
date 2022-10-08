package com.kaliondroid.techarena.data.api

sealed interface Response<T> {
    data class Success<T>(val data: T) : Response<T>
    data class Error(val exception: Exception) : Response<Nothing>
}

