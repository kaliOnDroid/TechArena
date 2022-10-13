package com.kaliondroid.techarena.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.kaliondroid.techarena.data.api.NewsApi
import com.kaliondroid.techarena.data.pagingsource.NewsListPagingSource
import com.kaliondroid.techarena.utils.LIMIT
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi
) {

    fun getNewsList() = Pager(
        pagingSourceFactory = { NewsListPagingSource(api) },
        config = PagingConfig(pageSize = LIMIT)
    ).flow
}