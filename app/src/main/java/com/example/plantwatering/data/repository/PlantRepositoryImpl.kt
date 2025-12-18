package com.example.plantwatering.data.repository

import com.example.plantwatering.data.remote.datasource.AuthRemoteDataSource
import com.example.plantwatering.data.remote.datasource.PlantRemoteDataSource
import com.example.plantwatering.data.remote.dto.toDomain
import com.example.plantwatering.domain.model.Plant
import com.example.plantwatering.domain.repository.PlantRepository
import com.example.plantwatering.data.remote.dto.toDto

class PlantRepositoryImpl(
    private val authDs: AuthRemoteDataSource,
    private val plantDs: PlantRemoteDataSource
) : PlantRepository {

    private suspend fun uid(): String = authDs.ensureSignedIn()

    override suspend fun createPlant(plant: Plant) {
        plantDs.createPlant(uid(), plant.toDto())
    }

    override suspend fun getPlants(): List<Plant> {
        return plantDs.getPlants(uid()).map { it.toDomain() }
    }

    override suspend fun getPlant(plantId: String): Plant? {
        return plantDs.getPlant(uid(), plantId)?.toDomain()
    }

    override suspend fun updatePlant(plantId: String, fields: Map<String, Any?>) {
        plantDs.updatePlant(uid(), plantId, fields)
    }
}
