package com.kaliondroid.techarena.data.models

data class Pagination(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val total: Int?
)