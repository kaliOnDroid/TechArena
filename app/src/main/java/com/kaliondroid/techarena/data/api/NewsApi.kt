package com.kaliondroid.techarena.data.api

import com.haroldadmin.cnradapter.NetworkResponse
import com.kaliondroid.techarena.data.models.ErrorResponse
import com.kaliondroid.techarena.data.models.NewsResponse
import com.kaliondroid.techarena.utils.API_KEY
import com.kaliondroid.techarena.utils.CATEGORY
import retrofit2.http.GET

interface NewsApi {

    @GET("news?access_key=$API_KEY&categories=$CATEGORY")
    suspend fun fetchTechNews(): NetworkResponse<NewsResponse, ErrorResponse>
}