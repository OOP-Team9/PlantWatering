package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.PlantDto

interface PlantRemoteDataSource {
    // // 조회
    suspend fun getPlants(uid: String): List<PlantDto>
    suspend fun getPlant(uid: String, plantId: String): PlantDto?

    // // 등록
    suspend fun createPlant(uid: String, plant: PlantDto)

    // // 수정(부분 수정 가능하게)
    suspend fun updatePlant(uid: String, plantId: String, fields: Map<String, Any?>)

    // // 삭제(선택)
    suspend fun deletePlant(uid: String, plantId: String)
}
