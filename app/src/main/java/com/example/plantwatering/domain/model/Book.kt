package com.example.plantwatering.domain.model

data class Book(
    val bookId: String,
    val plantName: String,
    val photoUri: String,
    val lightInfo: String,
    val waterInfo: String,
    val humidityInfo: String,
    val description: String
)

