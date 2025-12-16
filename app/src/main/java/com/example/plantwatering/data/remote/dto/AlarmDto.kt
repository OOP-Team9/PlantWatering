package com.example.plantwatering.data.remote.dto

import com.google.firebase.Timestamp

/**
 * Firestore 기준 알람 문서 모델.
 * - collection: /users/{uid}/alarms/{alarmId}
 */
data class AlarmDto(
    val alarmId: String = "",            // 문서 ID (string)
    val plantId: String? = null,         // 특정 식물에 연결할 경우 사용
    val hour: Int = 0,                   // 시
    val minute: Int = 0,                 // 분
    val isEnabled: Boolean = true,       // 활성화 여부
    val createdAt: Timestamp = Timestamp.now(),
    val updatedAt: Timestamp = Timestamp.now()
)