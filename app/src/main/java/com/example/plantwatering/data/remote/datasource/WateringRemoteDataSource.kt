package com.example.plantwatering.data.remote.datasource

import com.google.firebase.Timestamp

interface WateringRemoteDataSource {
    suspend fun waterPlant(
        uid: String,
        plantId: String,
        wateredAt: Timestamp,
        nextWateringAt: Timestamp
    )
}
