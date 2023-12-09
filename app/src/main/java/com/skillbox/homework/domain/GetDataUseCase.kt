package com.skillbox.homework.domain

import com.skillbox.homework.data.Repository
import com.skillbox.homework.entity.DataModel
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val repository: Repository) {

    suspend fun execute(): DataModel {
        return repository.getData()
    }
}