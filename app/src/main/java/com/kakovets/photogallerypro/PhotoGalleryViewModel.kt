package com.kakovets.photogallerypro

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class PhotoGalleryViewModel: ViewModel() {
    val pagingGalleryItemViewModel: Flow<PagingData<GalleryItem>> = FlickrFetchr().getItems()
}