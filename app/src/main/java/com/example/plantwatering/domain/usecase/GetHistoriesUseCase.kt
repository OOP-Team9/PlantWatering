package com.example.plantwatering.domain.usecase

import com.example.plantwatering.domain.model.WateringHistory
import com.example.plantwatering.domain.repository.HistoryRepository

class GetHistoriesUseCase(
    private val repo: HistoryRepository
) {
    suspend operator fun invoke(plantId: String): List<WateringHistory> = repo.getHistories(plantId)
}
