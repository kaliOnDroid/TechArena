package com.kaliondroid.techarena.data.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.kaliondroid.techarena.data.api.NewsApi
import com.kaliondroid.techarena.data.models.ErrorResponse
import com.kaliondroid.techarena.data.models.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi
) {

    fun getTechNews(): Flow<NetworkResponse<NewsResponse, ErrorResponse>> = flow {
        val techNews = api.fetchTechNews()
        emit(techNews)
    }.flowOn(Dispatchers.IO)
}