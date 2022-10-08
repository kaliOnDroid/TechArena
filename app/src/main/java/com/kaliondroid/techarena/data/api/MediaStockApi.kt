package com.kaliondroid.techarena.data.api

import com.kaliondroid.techarena.data.models.NewsResponse
import retrofit2.http.GET

interface MediaStockApi {

    @GET()
    suspend fun getNews(): Response<NewsResponse>
}