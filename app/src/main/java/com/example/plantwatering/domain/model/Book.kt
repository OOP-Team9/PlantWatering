package com.example.plantwatering.domain.model

data class Book(
    val bookId: String,
    val plantName: String,
    val plantEngName: String,
    val imageUrl: String,
    val lightInfo: String,
    val waterInfo: String,
    val humidityInfo: String,
    val description: String
)

