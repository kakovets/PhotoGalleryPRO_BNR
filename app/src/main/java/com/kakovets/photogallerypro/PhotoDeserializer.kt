package com.kakovets.photogallerypro

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.kakovets.photogallerypro.api.PhotoResponse
import java.lang.reflect.Type

class PhotoDeserializer: JsonDeserializer<PhotoResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PhotoResponse {
        val jobject = json.asJsonObject
        val jphotos = jobject.getAsJsonObject("photos")
        val jphotoArray = jphotos.getAsJsonArray("photo")
        val galleryList = mutableListOf<GalleryItem>()
        jphotoArray.forEach { item ->
            val gItem = GalleryItem(
                title = item.asJsonObject.get("title").asString,
                id = item.asJsonObject.get("id").asString,
                url = item.asJsonObject.get("url_s").asString
            )
            galleryList.add(gItem)
        }
        return PhotoResponse(galleryList)
    }
}