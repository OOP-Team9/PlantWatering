package com.example.plantwatering.data.repository

import com.example.plantwatering.data.remote.datasource.TipRemoteDataSource
import com.example.plantwatering.data.remote.dto.toDomain
import com.example.plantwatering.domain.model.Tip
import com.example.plantwatering.domain.repository.TipRepository

class TipRepositoryImpl(
    private val tipDs: TipRemoteDataSource
) : TipRepository {

    override suspend fun getTip(tipId: String): Tip? {
        val tip = tipDs.getTip(tipId)
        if (tip != null) return tip.toDomain()
        // fallback: 첫 번째 문서라도 반환
        return null
    }
}

