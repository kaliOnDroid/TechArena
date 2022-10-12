package com.kaliondroid.techarena.data.models

data class NewsResponse(
    val `data`: List<NewsItem>?,
    val pagination: Pagination?
)