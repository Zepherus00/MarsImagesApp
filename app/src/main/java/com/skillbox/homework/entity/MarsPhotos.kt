package com.skillbox.homework.entity

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MarsPhotos(
    val id: Int?,
    val sol: Int?,
    val camera: Camera?,
    val img_src: String?,
    val earth_date: String?,
    val rover: Rover?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Camera(
    val id: Int,
    val name: String,
    val rover_id: Int,
    val full_name: String
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Rover(
    val id: Int,
    val name: String,
    val landing_date: String,
    val launch_date: String,
    val status: String
) : Parcelable