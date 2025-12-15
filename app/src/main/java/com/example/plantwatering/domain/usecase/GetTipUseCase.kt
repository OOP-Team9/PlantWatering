package com.example.plantwatering.domain.usecase

import com.example.plantwatering.domain.model.Tip
import com.example.plantwatering.domain.repository.TipRepository

class GetTipUseCase(
    private val repo: TipRepository
) {
    suspend operator fun invoke(tipId: String = "default"): Tip? = repo.getTip(tipId)
}

