package com.example.plantwatering.data.remote.dto

import com.google.firebase.Timestamp

/**
 * Firestore 기준 식물 문서 모델.
 * - collection: /users/{uid}/plants/{plantId}
 */
data class PlantDto(
    val plantId: String = "",                 // 문서 ID (string)
    val name: String = "",                    // 식물 이름
    val wateringIntervalDays: Int = 1,        // 물 주기(일)
    val lastWateredAt: Timestamp = Timestamp.now(),   // 마지막 급수일
    val nextWateringAt: Timestamp = Timestamp.now(),  // 다음 급수 예정일
    val photoUrl: String? = null,             // 사진 URL (nullable)
    val species: String? = null,              // 식물 종 (nullable)
    val dailyLog: String? = null,             // 일지 (nullable)
    val wateringStatus: Boolean = false,      // 오늘/예정 급수 필요 여부
    val createdAt: Timestamp = Timestamp.now(), // 생성 시각
    val updatedAt: Timestamp = Timestamp.now()  // 수정 시각
)
