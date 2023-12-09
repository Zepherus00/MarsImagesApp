package com.skillbox.homework.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataModel(
    val photos: List<MarsPhotos>
)