package com.example.plantwatering.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.plantwatering.data.remote.datasource.AuthRemoteDataSource
import com.example.plantwatering.data.remote.datasource.PlantRemoteDataSource
import com.example.plantwatering.data.remote.datasource.WateringRemoteDataSource
import com.example.plantwatering.domain.repository.WateringRepository
import com.google.firebase.Timestamp
import java.time.temporal.ChronoUnit
import java.util.Date

class WateringRepositoryImpl(
    private val authDs: AuthRemoteDataSource,
    private val plantDs: PlantRemoteDataSource,
    private val wateringDs: WateringRemoteDataSource
) : WateringRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun waterPlant(plantId: String) {
        val uid = authDs.ensureSignedIn()

        // 현재 plant 읽어서 interval 기반으로 next 계산
        val plant = plantDs.getPlant(uid, plantId)
            ?: throw IllegalStateException("Plant not found: $plantId")

        val interval = plant.wateringIntervalDays.coerceAtLeast(1)
        val now = java.time.Instant.now()
        val next = now.plus(interval.toLong(), ChronoUnit.DAYS)

        wateringDs.waterPlant(
            uid = uid,
            plantId = plantId,
            wateredAt = Timestamp(Date.from(now)),
            nextWateringAt = Timestamp(Date.from(next))
        )
    }
}
