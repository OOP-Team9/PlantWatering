package com.example.plantwatering.domain.model

import java.time.Instant

data class WateringHistory(
    val historyId: String,
    val plantId: String,
    val wateredAt: Instant
)