package com.kakovets.photogallerypro

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.GsonBuilder
import com.kakovets.photogallerypro.api.FlickrApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlickrFetchr {
    private val flickrApi: FlickrApi

    init {
        val gson = GsonBuilder().registerTypeAdapter(List::class.java, PhotoDeserializer()).create()
        val gsonFactory = GsonConverterFactory.create(gson)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(gsonFactory)
            .build()
        flickrApi = retrofit.create(FlickrApi::class.java)
    }

    fun getItems(): Flow<PagingData<GalleryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50,
                enablePlaceholders = false,
                prefetchDistance = 15
            ),
            pagingSourceFactory = { PhotoPagingSource(flickrApi) }
        ).flow
    }
}