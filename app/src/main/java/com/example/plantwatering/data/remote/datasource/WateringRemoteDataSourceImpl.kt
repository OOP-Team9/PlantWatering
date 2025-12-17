package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.WateringHistoryDto
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

class WateringRemoteDataSourceImpl(
    private val db: FirebaseFirestore
) : WateringRemoteDataSource {

    private fun plantDoc(uid: String, plantId: String) =
        db.collection("users").document(uid)
            .collection("plants").document(plantId)

    private fun historiesCol(uid: String, plantId: String) =
        plantDoc(uid, plantId).collection("histories")

    override suspend fun waterPlant(
        uid: String,
        plantId: String,
        wateredAt: Timestamp,
        nextWateringAt: Timestamp
    ) {
        val plant = plantDoc(uid, plantId)

        plant.update(
            mapOf(
                "lastWateredAt" to wateredAt,
                "nextWateringAt" to nextWateringAt,
                "wateringStatus" to true,
                "updatedAt" to Timestamp.now()
            )
        ).await()
        
        val historyId = UUID.randomUUID().toString()
        val history = historiesCol(uid, plantId).document(historyId)

        val newHistory = WateringHistoryDto(
            historyId = historyId,
            plantId = plantId,
            wateredAt = wateredAt
        )
        history.set(newHistory).await()
    }
}
