package com.example.plantwatering.domain.repository

import com.example.plantwatering.domain.model.Tip

interface TipRepository {
    suspend fun getTip(tipId: String = "default"): Tip?
}

