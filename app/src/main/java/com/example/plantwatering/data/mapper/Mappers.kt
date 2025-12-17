package com.example.plantwatering.data.remote.dto

import com.example.plantwatering.domain.model.Plant
import com.example.plantwatering.domain.model.WateringHistory
import com.example.plantwatering.domain.model.Tip
import com.example.plantwatering.domain.model.Book
import com.example.plantwatering.presentation.screen.watering.components.PlantUi
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.time.ZoneId
private val sdf = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
private val zone = ZoneId.systemDefault()

fun PlantDto.toUi(): PlantUi {
    val next = nextWateringAt.toDate()

    return PlantUi(
        plantId = plantId,
        name = name,
        wateringIntervalDays = wateringIntervalDays,
        nextWateringDate = next.toInstant().atZone(zone).toLocalDate(),
        lastWateredDate = lastWateredAt.toDate().toInstant().atZone(zone).toLocalDate(),
        wateringStatus = wateringStatus,
        imageUrl = imageUrl
    )
}
fun Plant.toUi(): PlantUi {
    val next = Date.from(nextWateringAt)

    return PlantUi(
        plantId = plantId,
        name = name,
        wateringIntervalDays = wateringIntervalDays,
        nextWateringDate = next.toInstant().atZone(zone).toLocalDate(),
        lastWateredDate = lastWateredAt.atZone(zone).toLocalDate(),
        wateringStatus = wateringStatus,
        imageUrl = imageUrl
    )
}
fun PlantDto.toDomain(): Plant = Plant(
    plantId = plantId,
    name = name,
    wateringIntervalDays = wateringIntervalDays,
    lastWateredAt = lastWateredAt.toDate().toInstant(),
    nextWateringAt = nextWateringAt.toDate().toInstant(),
    imageUrl = imageUrl,
    species = species,
    dailyLog = dailyLog,
    wateringStatus = wateringStatus
)

fun Plant.toDto(): PlantDto = PlantDto(
    plantId = plantId,
    name = name,
    wateringIntervalDays = wateringIntervalDays,
    lastWateredAt = Timestamp(Date.from(lastWateredAt)),
    nextWateringAt = Timestamp(Date.from(nextWateringAt)),
    imageUrl = imageUrl,
    species = species,
    dailyLog = dailyLog,
    wateringStatus = wateringStatus
)

fun WateringHistoryDto.toDomain(): WateringHistory = WateringHistory(
    historyId = historyId,
    plantId = plantId,
    wateredAt = wateredAt.toDate().toInstant()
)

fun WateringHistory.toDto(): WateringHistoryDto = WateringHistoryDto(
    historyId = historyId,
    plantId = plantId,
    wateredAt = Timestamp(Date.from(wateredAt))
)

fun TipDto.toDomain(): Tip = Tip(
    tipId = tipId,
    content = content
)

fun BookDto.toDomain(): Book = Book(
    bookId = bookId,
    plantName = plantName,
    plantEngName = plantEngName,
    imageUrl = imageUrl,
    lightInfo = lightInfo,
    waterInfo = waterInfo,
    humidityInfo = humidityInfo,
    description = description
)
