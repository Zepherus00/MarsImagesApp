package com.skillbox.homework.data

import com.skillbox.homework.entity.DataModel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.nasa.gov"

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchNasaApi: SearchApi = retrofit.create(SearchApi::class.java)
}

interface SearchApi {
    @GET("/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=KhAJLBODNZNkRzS2a0xmiGuKdvw0d5g7lqvLFpsz")
    suspend fun getData(): DataModel
}