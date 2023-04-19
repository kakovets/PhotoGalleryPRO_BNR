package com.kakovets.photogallerypro

import com.google.gson.annotations.SerializedName

data class GalleryItem(
    var title: String = "",
    var id: Int,
    @SerializedName("url_s") var url: String = ""
)