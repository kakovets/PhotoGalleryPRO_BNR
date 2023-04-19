package com.kakovets.photogallerypro

import androidx.paging.*
import com.kakovets.photogallerypro.api.FlickrApi

private const val TAG = "MyPagingSource"

class PhotoPagingSource(private val myApi: FlickrApi) : PagingSource<Int, GalleryItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val response = myApi.getItems(page = page, pageSize = pageSize)

            if (response.isSuccessful) {
                val items = response.body() ?: emptyList()
                LoadResult.Page(
                    data = items,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (items.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception("Failed to load data"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}