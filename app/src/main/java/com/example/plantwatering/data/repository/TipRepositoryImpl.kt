package com.example.plantwatering.data.repository

import com.example.plantwatering.data.remote.datasource.TipRemoteDataSource
import com.example.plantwatering.data.remote.dto.toDomain
import com.example.plantwatering.domain.model.Tip
import com.example.plantwatering.domain.repository.TipRepository

class TipRepositoryImpl(
    private val tipDs: TipRemoteDataSource
) : TipRepository {

    override suspend fun getTip(tipId: String): Tip? {
        val list = tipDs.getTips()
        return list.firstOrNull { it.tipId == tipId }?.toDomain()
            ?: list.firstOrNull()?.toDomain()
    }

    override suspend fun getTips(): List<Tip> =
        tipDs.getTips().mapNotNull { it.toDomain() }
}

