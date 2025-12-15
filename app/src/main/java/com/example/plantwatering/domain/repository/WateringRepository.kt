package com.example.plantwatering.domain.repository

interface WateringRepository {
    suspend fun waterPlant(plantId: String)
}
