package com.kakovets.photogallerypro.api

import com.kakovets.photogallerypro.GalleryItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=e4d50de0aae439c51e1d60f6292c6e57" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )
    suspend fun getItems(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Response<List<GalleryItem>>
}