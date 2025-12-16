package com.example.plantwatering.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantwatering.domain.model.Plant
import com.example.plantwatering.domain.usecase.GetPlantsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class HomeUiState(
    val isLoading: Boolean = false,
    val plants: List<Plant> = emptyList(),
    val error: String? = null
)


class HomeViewModel(
    private val getPlantsUseCase: GetPlantsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val dateFormatter: DateTimeFormatter =
        DateTimeFormatter
            .ofPattern("yyyy.MM.dd")
            .withZone(ZoneId.systemDefault())

    fun loadPlants() {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)

            runCatching {
                getPlantsUseCase()
            }.onSuccess { plants ->
                val sortedPlants = plants.sortedBy { it.nextWateringAt }
                _uiState.value = HomeUiState(
                    plants = plants.sortedBy { it.nextWateringAt }
                )
            }.onFailure { e ->
                _uiState.value = HomeUiState(
                    error = e.message ?: "식물 목록을 불러오지 못했어요"
                )
            }

            fun formatDate(instant: Instant): String {
                return dateFormatter.format(instant)
            }
        }
    }
}