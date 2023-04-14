package com.kakovets.photogallerypro

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PhotoGalleryViewModel: ViewModel() {

    val galleryItemViewModel: LiveData<List<GalleryItem>>

    init {
        galleryItemViewModel = FlickrFetchr().fetchPhotos()
    }
}