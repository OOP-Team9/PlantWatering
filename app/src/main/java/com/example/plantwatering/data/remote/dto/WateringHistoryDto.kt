package com.example.plantwatering.data.remote.dto

import com.google.firebase.Timestamp

data class WateringHistoryDto(
    val historyId: String = "",
    val plantId: String = "",
    val wateredAt: Timestamp = Timestamp.now()
)