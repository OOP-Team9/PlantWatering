package com.example.plantwatering.presentation.screen.watering.components


import java.time.LocalDate

data class Plant(
    var name: String,
    val imageUrl: String,
    var wateringInterval: Int,
    var nextWateringDate: String, //실제론 Date?
    var wateringStatus : Boolean
)

val plants = listOf(
    Plant(
        name = "몬스테라",
        imageUrl = "url",
        wateringInterval =  7, // 7 단위로 주일로 바꿔주는 것도 필요하겠구나
        nextWateringDate =  "2025.11.29",
        wateringStatus = false
    ),
    Plant(
        name = "인도고무나무",
        imageUrl = "url",
        wateringInterval =  7,
        nextWateringDate =  "2025.11.28",
        wateringStatus = false
    ),
    Plant(
        name = "로즈마리",
        imageUrl = "url",
        wateringInterval =  7,
        nextWateringDate =  "2025.11.29",
        wateringStatus = true
    ),
    Plant(
        name = "로즈마리",
        imageUrl = "url",
        wateringInterval =  7,
        nextWateringDate =  "2025.11.29",
        wateringStatus = true
    )
)