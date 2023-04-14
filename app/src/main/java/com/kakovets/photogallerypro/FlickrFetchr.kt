package com.kakovets.photogallerypro

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.kakovets.photogallerypro.api.FlickrApi
import com.kakovets.photogallerypro.api.PhotoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "FlickrFetchr"

class FlickrFetchr {
    private val flickrApi: FlickrApi
    init {
        val gson = GsonBuilder().registerTypeAdapter(PhotoResponse::class.java, PhotoDeserializer()).create()
        val gsonFactory = GsonConverterFactory.create(gson)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(gsonFactory)
            .build()
        flickrApi = retrofit.create(FlickrApi::class.java)
    }

    fun fetchPhotos(): LiveData<List<GalleryItem>> {
        val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()
        val flickrRequest: Call<PhotoResponse> = flickrApi.fetchPhotos()

        flickrRequest.enqueue(object : Callback<PhotoResponse> {

            override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                Log.e(TAG, "Failed", t)
            }

            override fun onResponse(call: Call<PhotoResponse>, response: Response<PhotoResponse>) {
                val photoResponse: PhotoResponse? = response.body()
                var galleryItems: List<GalleryItem> = photoResponse?.galleryItems ?: mutableListOf()
                galleryItems = galleryItems.filterNot{
                    it.url.isBlank()
                }

                responseLiveData.value = galleryItems
                Log.d(TAG, "Responseeee received: ${response.body()}")
            }
        })
        return responseLiveData
    }
}