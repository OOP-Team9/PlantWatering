package com.example.plantwatering.presentation.screen.watering.components

import java.time.LocalDate


data class PlantUi(
    val plantId: String = "",
    val name: String,
    val imageUrl: String? = null,
    val wateringIntervalDays: Int,
    val nextWateringDate: java.time.LocalDate, // 정렬/표시용
    val lastWateredDate: java.time.LocalDate,  // 오늘 물 줬는지 판단용
    val wateringStatus: Boolean
)

val plants = listOf(
    PlantUi("1", "몬스테라", null, 7,
        LocalDate.now(), LocalDate.now(), false),
    PlantUi("2", "인도고무나무", null, 7,
        LocalDate.now(), LocalDate.now(), false),
    PlantUi("3", "로즈마리", null, 7,
        LocalDate.now(), LocalDate.now(), true),
    PlantUi("4", "로즈마리", null, 7,
        LocalDate.now(), LocalDate.now(), true)
)