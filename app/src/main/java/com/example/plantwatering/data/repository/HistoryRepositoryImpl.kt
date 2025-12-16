package com.example.plantwatering.data.repository

import com.example.plantwatering.data.remote.datasource.AuthRemoteDataSource
import com.example.plantwatering.data.remote.datasource.HistoryRemoteDataSource
import com.example.plantwatering.data.remote.dto.toDomain
import com.example.plantwatering.domain.model.WateringHistory
import com.example.plantwatering.domain.repository.HistoryRepository

class HistoryRepositoryImpl(
    private val authDs: AuthRemoteDataSource,
    private val historyDs: HistoryRemoteDataSource
) : HistoryRepository {

    override suspend fun getHistories(plantId: String): List<WateringHistory> {
        val uid = authDs.ensureSignedIn()
        return historyDs.getHistories(uid, plantId).map { it.toDomain() }
    }
}
