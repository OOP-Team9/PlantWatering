package com.example.plantwatering.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.plantwatering.data.remote.datasource.AuthRemoteDataSource
import com.example.plantwatering.data.remote.datasource.PlantRemoteDataSource
import com.example.plantwatering.data.remote.datasource.WateringRemoteDataSource
import com.example.plantwatering.domain.repository.WateringRepository
import com.google.firebase.Timestamp
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date

class WateringRepositoryImpl(
    private val authDs: AuthRemoteDataSource,
    private val plantDs: PlantRemoteDataSource,
    private val wateringDs: WateringRemoteDataSource
) : WateringRepository {

    override suspend fun waterPlant(plantId: String) {
        val uid = authDs.ensureSignedIn()
        val plant = plantDs.getPlant(uid, plantId)

        val intervalDays: Int = plant?.wateringIntervalDays?.coerceAtLeast(1)?:1

        // 현재 시각 = 물 주기 실행 시각
        val nowDate = Date()

        // 다음 급수 날짜 계산
        val cal = Calendar.getInstance().apply {
            time = Date()
            add(Calendar.DAY_OF_YEAR, intervalDays)
        }

        val nextDate = cal.time

        wateringDs.waterPlant(
            uid = uid,
            plantId = plantId,
            wateredAt = Timestamp(nowDate),
            nextWateringAt = Timestamp(nextDate)
        )
    }
}
