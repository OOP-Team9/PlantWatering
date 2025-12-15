package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.AlarmDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AlarmRemoteDataSourceImpl(
    private val db: FirebaseFirestore
) : AlarmRemoteDataSource {

    private fun alarmsCol(uid: String) =
        db.collection("users").document(uid).collection("alarms")

    override suspend fun getAlarms(uid: String): List<AlarmDto> {
        val snap = alarmsCol(uid)
            .orderBy("hour")
            .get()
            .await()

        return snap.documents.mapNotNull { doc ->
            doc.toObject(AlarmDto::class.java)?.copy(alarmId = doc.id)
        }
    }

    override suspend fun createAlarm(uid: String, alarm: AlarmDto) {
        val docId = alarm.alarmId.ifEmpty { alarmsCol(uid).document().id }
        alarmsCol(uid).document(docId).set(alarm.copy(alarmId = docId)).await()
    }

    override suspend fun updateAlarm(uid: String, alarmId: String, fields: Map<String, Any?>) {
        alarmsCol(uid).document(alarmId).update(fields).await()
    }

    override suspend fun deleteAlarm(uid: String, alarmId: String) {
        alarmsCol(uid).document(alarmId).delete().await()
    }
}

