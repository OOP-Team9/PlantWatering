package com.example.plantwatering.domain.model

import java.time.Instant

data class Plant(
    val plantId: String,
    val name: String,
    val wateringIntervalDays: Int,
    val lastWateredAt: Instant,
    val nextWateringAt: Instant,
    val imageUrl: String?,
    val species: String?,
    val dailyLog: String? = null,
    val wateringStatus: Boolean = false
)
