package com.example.plantwatering.domain.usecase

import com.example.plantwatering.domain.repository.WateringRepository

class WaterPlantUseCase(
    private val repo: WateringRepository
) {
    suspend operator fun invoke(plantId: String) = repo.waterPlant(plantId)
}
