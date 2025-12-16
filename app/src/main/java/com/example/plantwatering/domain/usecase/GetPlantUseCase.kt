package com.example.plantwatering.domain.usecase

import com.example.plantwatering.domain.model.Plant
import com.example.plantwatering.domain.repository.PlantRepository

class GetPlantUseCase(
    private val repo: PlantRepository
) {
    suspend operator fun invoke(plantId: String): Plant? = repo.getPlant(plantId)
}

