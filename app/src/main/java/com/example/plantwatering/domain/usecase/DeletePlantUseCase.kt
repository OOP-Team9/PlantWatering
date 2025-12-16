package com.example.plantwatering.domain.usecase

import com.example.plantwatering.domain.repository.PlantRepository

class DeletePlantUseCase(
    private val repo: PlantRepository
) {
    suspend operator fun invoke(plantId: String) {
        repo.deletePlant(plantId)
    }
}

