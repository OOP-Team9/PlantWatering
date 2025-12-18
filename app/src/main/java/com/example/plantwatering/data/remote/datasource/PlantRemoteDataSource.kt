package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.PlantDto

interface PlantRemoteDataSource {
    suspend fun getPlants(uid: String): List<PlantDto>
    suspend fun getPlant(uid: String, plantId: String): PlantDto?
    suspend fun createPlant(uid: String, plant: PlantDto)
    suspend fun updatePlant(uid: String, plantId: String, fields: Map<String, Any?>)
}
