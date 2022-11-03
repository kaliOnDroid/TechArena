package com.kaliondroid.techarena.data.api

import com.kaliondroid.techarena.data.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines?category=technology&language=en")
    suspend fun fetchNews(
        @Query("page") page: Int
    ): NewsResponse
}