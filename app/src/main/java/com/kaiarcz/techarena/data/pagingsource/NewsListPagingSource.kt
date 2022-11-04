package com.kaiarcz.techarena.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kaiarcz.techarena.data.api.NewsApi
import com.kaiarcz.techarena.data.models.Article

class NewsListPagingSource(
  private val api: NewsApi
) : PagingSource<Int, Article>() {
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
    return try {
      // Start refresh at page 1 if undefined.
      val page = params.key ?: 1
      val response = api.fetchNews(page)

      val nextKey = if (response.articles.isEmpty()) null else page + 1
       LoadResult.Page(
        data = response.articles,
        prevKey = null, // Only paging forward.
        nextKey = nextKey
      )
    } catch (e: Exception) {
      // Handle errors in this block and return LoadResult.Error if it is an
      // expected error (such as a network failure).
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
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