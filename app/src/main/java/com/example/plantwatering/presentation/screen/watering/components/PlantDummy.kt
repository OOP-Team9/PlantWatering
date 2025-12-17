package com.example.plantwatering.presentation.screen.watering.components


data class PlantUi(
    val plantId: String = "",
    val name: String,
    val imageUrl: String? = null,
    val wateringIntervalDays: Int,
    val nextWateringDate: String, // UI 표기용 날짜 문자열
    val nextWateringAt: Long, // 정렬/상태 계산용 (epoch millis)
    val lastWateredAt: Long,  // 오늘 물 줬는지 판단용 (epoch millis)
    val wateringStatus: Boolean
)

private val nowMillis = System.currentTimeMillis()
private fun daysFromNow(days: Int): Long =
    nowMillis + days * 24L * 60L * 60L * 1000L

val plants = listOf(
    PlantUi("1", "몬스테라", null, 7, "2025.11.29", daysFromNow(0), nowMillis, false),
    PlantUi("2", "인도고무나무", null, 7, "2025.11.28", daysFromNow(1), nowMillis, false),
    PlantUi("3", "로즈마리", null, 7, "2025.11.29", daysFromNow(0), nowMillis, true),
    PlantUi("4", "로즈마리", null, 7, "2025.11.29", daysFromNow(2), nowMillis, true)
)