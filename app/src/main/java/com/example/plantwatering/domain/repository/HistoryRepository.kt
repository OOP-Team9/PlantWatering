package com.example.plantwatering.domain.repository

import com.example.plantwatering.domain.model.WateringHistory

interface HistoryRepository {
    suspend fun getHistories(plantId: String): List<WateringHistory>
}
