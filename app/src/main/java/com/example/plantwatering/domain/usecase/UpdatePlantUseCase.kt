package com.example.plantwatering.domain.usecase

import com.example.plantwatering.domain.repository.PlantRepository

class UpdatePlantUseCase(
    private val repo: PlantRepository
) {
    suspend operator fun invoke(plantId: String, fields: Map<String, Any?>) {
        repo.updatePlant(plantId, fields)
    }
}

