package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.PlantDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PlantRemoteDataSourceImpl(
    private val db: FirebaseFirestore
) : PlantRemoteDataSource {

    private fun plantsCol(uid: String) =
        db.collection("users").document(uid).collection("plants")

    override suspend fun getPlants(uid: String): List<PlantDto> {
        val snap = plantsCol(uid)
            .orderBy("nextWateringAt")
            .get()
            .await()

        return snap.documents.mapNotNull { doc ->
            doc.toObject(PlantDto::class.java)?.copy(plantId = doc.id)
        }
    }

    override suspend fun getPlant(uid: String, plantId: String): PlantDto? {
        val doc = plantsCol(uid).document(plantId).get().await()
        return doc.toObject(PlantDto::class.java)?.copy(plantId = doc.id)
    }

    override suspend fun createPlant(uid: String, plant: PlantDto) {
        val docId = plant.plantId.ifEmpty { plantsCol(uid).document().id }
        val now = com.google.firebase.Timestamp.now()
        plantsCol(uid).document(docId).set(
            plant.copy(
                plantId = docId,
                createdAt = now,
                updatedAt = now
            )
        ).await()
    }

    override suspend fun updatePlant(uid: String, plantId: String, fields: Map<String, Any?>) {
        plantsCol(uid).document(plantId)
            .update(fields + ("updatedAt" to com.google.firebase.Timestamp.now()))
            .await()
    }
}
