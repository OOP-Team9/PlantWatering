package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.WateringHistoryDto

interface HistoryRemoteDataSource {
    suspend fun getHistories(uid: String, plantId: String): List<WateringHistoryDto>
}
