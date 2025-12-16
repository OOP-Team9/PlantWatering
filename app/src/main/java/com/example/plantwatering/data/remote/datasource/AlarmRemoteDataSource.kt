package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.AlarmDto

interface AlarmRemoteDataSource {
    suspend fun getAlarms(uid: String): List<AlarmDto>
    suspend fun createAlarm(uid: String, alarm: AlarmDto)
    suspend fun updateAlarm(uid: String, alarmId: String, fields: Map<String, Any?>)
    suspend fun deleteAlarm(uid: String, alarmId: String)
}

