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

    override suspend fun createPlant(plant: Plant) {
        val uid = authDs.ensureSignedIn()
        plantDs.createPlant(uid, plant.toDto())
    }

    override suspend fun getPlants(): List<Plant> {
        val uid = authDs.ensureSignedIn()
        return plantDs.getPlants(uid).map { it.toDomain() }
    }
}
