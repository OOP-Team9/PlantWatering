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
        val pRef = plantDoc(uid, plantId)
        val historyId = UUID.randomUUID().toString()
        val hRef = historiesCol(uid, plantId).document(historyId)

        val history = WateringHistoryDto(
            historyId = historyId,
            plantId = plantId,
            wateredAt = wateredAt,
            uid = uid
        )

        db.runBatch { batch ->
            batch.update(pRef, mapOf(
                "lastWateredAt" to wateredAt,
                "nextWateringAt" to nextWateringAt,
                // 물을 준 순간이므로 상태를 true(급수 완료)로 반영
                "wateringStatus" to true,
                "updatedAt" to com.google.firebase.Timestamp.now()
            ))
            batch.set(hRef, history)
        }.await()
    }
}
