package com.kaliondroid.techarena.data.api

import com.kaliondroid.techarena.data.models.NewsResponse
import com.kaliondroid.techarena.utils.API_KEY
import com.kaliondroid.techarena.utils.CATEGORY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("news?access_key=$API_KEY&categories=$CATEGORY&languages=en")
    suspend fun fetchTechNews(
        @Query("offset") offset: Int
    ): NewsResponse
}