package com.kaliondroid.techarena.data.models

data class NewsResponse(
    val `data`: List<Data>?,
    val pagination: Pagination?
)