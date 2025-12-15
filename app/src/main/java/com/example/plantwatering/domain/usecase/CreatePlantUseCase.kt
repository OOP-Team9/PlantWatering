package com.example.plantwatering.domain.usecase

import com.example.plantwatering.domain.model.Plant
import com.example.plantwatering.domain.repository.PlantRepository

class CreatePlantUseCase(
    private val repo: PlantRepository
) {
    suspend operator fun invoke(plant: Plant) {
        repo.createPlant(plant)
    }
}