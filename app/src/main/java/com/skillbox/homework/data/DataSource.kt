package com.skillbox.homework.data

import com.skillbox.homework.entity.DataModel
import com.skillbox.homework.entity.MarsPhotos
import javax.inject.Inject

open class DataSource @Inject constructor() {

    suspend fun loadData(): DataModel {
        val data: DataModel?
        try {
            data = RetrofitInstance.searchNasaApi.getData()
        } catch (t: Throwable) {
            return DataModel(listOf(MarsPhotos(null, null, null, null, "00", null)))
        }
        return data
    }
}