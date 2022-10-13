package com.kaliondroid.techarena.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kaliondroid.techarena.data.api.NewsApi
import com.kaliondroid.techarena.data.models.NewsItem

class NewsListPagingSource(
  private val api: NewsApi
) : PagingSource<Int, NewsItem>() {
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsItem> {
    return try {
      // Start refresh at page 1 if undefined.
      val nextOffset = params.key ?: 0
      val response = api.fetchTechNews(nextOffset)
       LoadResult.Page(
        data = response.data ?: emptyList(),
        prevKey = null, // Only paging forward.
        nextKey = response.pagination?.offset
      )
    } catch (e: Exception) {
      // Handle errors in this block and return LoadResult.Error if it is an
      // expected error (such as a network failure).
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, NewsItem>): Int? {
    // Try to find the page key of the closest page to anchorPosition, from
    // either the prevKey or the nextKey, but you need to handle nullability
    // here:
    //  * prevKey == null -> anchorPage is the first page.
    //  * nextKey == null -> anchorPage is the last page.
    //  * both prevKey and nextKey null -> anchorPage is the initial page, so
    //    just return null.
    return null
  }
}