package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.TipDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TipRemoteDataSourceImpl(
    private val db: FirebaseFirestore
) : TipRemoteDataSource {

    private fun tipsCol() = db.collection("tips")

    override suspend fun getTips(): List<TipDto> {
        val snap = tipsCol()
            .orderBy("tipId")
            .get()
            .await()

        return snap.documents.mapNotNull { doc ->
            doc.toObject(TipDto::class.java)?.copy(tipId = doc.id)
        }
    }
}

