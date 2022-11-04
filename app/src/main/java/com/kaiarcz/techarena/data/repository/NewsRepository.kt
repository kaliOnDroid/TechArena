package com.kaiarcz.techarena.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.kaiarcz.techarena.data.api.NewsApi
import com.kaiarcz.techarena.data.pagingsource.NewsListPagingSource
import com.kaiarcz.techarena.utils.NETWORK_PAGE_SIZE
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi
) {

    fun getNewsList() = Pager(
        pagingSourceFactory = { NewsListPagingSource(api) },
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE)
    ).flow
}