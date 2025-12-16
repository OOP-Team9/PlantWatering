package com.example.plantwatering.domain.repository

import com.example.plantwatering.domain.model.Plant

interface PlantRepository {
    suspend fun createPlant(plant: Plant)
    suspend fun getPlants(): List<Plant>
    suspend fun getPlant(plantId: String): Plant?
    suspend fun updatePlant(plantId: String, fields: Map<String, Any?>)
    suspend fun deletePlant(plantId: String)
}
