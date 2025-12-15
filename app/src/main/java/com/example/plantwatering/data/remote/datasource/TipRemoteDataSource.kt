package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.TipDto

interface TipRemoteDataSource {
    suspend fun getTips(): List<TipDto>
}

