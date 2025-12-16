package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.WateringHistoryDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HistoryRemoteDataSourceImpl(
    private val db: FirebaseFirestore
) : HistoryRemoteDataSource {

    private fun historiesCol(uid: String, plantId: String) =
        db.collection("users").document(uid)
            .collection("plants").document(plantId)
            .collection("histories")

    override suspend fun getHistories(uid: String, plantId: String): List<WateringHistoryDto> {
        val snap = historiesCol(uid, plantId)
            .orderBy("wateredAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .limit(10)
            .get()
            .await()

        return snap.documents.mapNotNull { it.toObject(WateringHistoryDto::class.java) }
    }
}
