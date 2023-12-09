package com.skillbox.homework.data

import com.skillbox.homework.entity.DataModel
import javax.inject.Inject

open class Repository @Inject constructor(private val data: DataSource) {

    suspend fun getData(): DataModel {
        return data.loadData()
    }
}