package com.example.plantwatering.domain.repository

import com.example.plantwatering.domain.model.Plant

interface PlantRepository {
    suspend fun createPlant(plant: Plant)
    suspend fun getPlants(): List<Plant>
}
