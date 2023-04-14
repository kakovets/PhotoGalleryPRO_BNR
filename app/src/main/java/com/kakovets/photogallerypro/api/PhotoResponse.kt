package com.kakovets.photogallerypro.api

import com.google.gson.annotations.SerializedName
import com.kakovets.photogallerypro.GalleryItem

class PhotoResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<GalleryItem>
}