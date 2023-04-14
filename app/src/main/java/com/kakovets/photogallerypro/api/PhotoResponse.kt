package com.kakovets.photogallerypro.api

import com.google.gson.annotations.SerializedName
import com.kakovets.photogallerypro.GalleryItem

class PhotoResponse(list: List<GalleryItem>) {
    @SerializedName("photo")
    var galleryItems = list
}