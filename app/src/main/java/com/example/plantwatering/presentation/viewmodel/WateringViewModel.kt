package com.example.plantwatering.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantwatering.domain.model.Plant
import com.example.plantwatering.domain.model.WateringHistory
import com.example.plantwatering.domain.usecase.GetPlantsUseCase
import com.example.plantwatering.domain.usecase.GetHistoriesUseCase
import com.example.plantwatering.domain.usecase.WaterPlantUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class WateringUiState(
    val isLoading: Boolean = false,
    val isWatering: Boolean = false,
    val plants: List<Plant> = emptyList(),
    val histories: List<WateringHistory> = emptyList(),
    val selectedPlantId: String? = null,
    val error: String? = null
)

class WateringViewModel(
    private val getPlantsUseCase: GetPlantsUseCase,
    private val waterPlantUseCase: WaterPlantUseCase,
    private val getHistoriesUseCase: GetHistoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WateringUiState())
    val uiState: StateFlow<WateringUiState> = _uiState.asStateFlow()

    fun loadPlants() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            runCatching { getPlantsUseCase() }
                .onSuccess { list ->
                    val sorted = list.sortedBy { it.nextWateringAt }
                    val currentSelected = _uiState.value.selectedPlantId
                    val newSelected = currentSelected ?: sorted.firstOrNull()?.plantId
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        plants = sorted,
                        selectedPlantId = newSelected
                    )
                    newSelected?.let { loadHistories(it) }
                }
                .onFailure { e ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.message ?: "식물 불러오기 실패"
                    )
                }
        }
    }

    fun loadHistories(plantId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            runCatching { getHistoriesUseCase(plantId) }
                .onSuccess { list ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        histories = list,
                        selectedPlantId = plantId
                    )
                }
                .onFailure { e ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.message ?: "히스토리 불러오기 실패"
                    )
                }
        }
    }

    fun waterPlant(plantId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isWatering = true, error = null)
            runCatching { waterPlantUseCase(plantId) }
                .onSuccess {
                    // 물 주기 성공 후 목록 갱신
                    runCatching { getPlantsUseCase() }
                        .onSuccess { list ->
                            _uiState.value = _uiState.value.copy(
                                isWatering = false,
                                plants = list,
                                selectedPlantId = plantId
                            )
                        }
                        .onFailure { e ->
                            _uiState.value = _uiState.value.copy(
                                isWatering = false,
                                error = e.message ?: "식물 불러오기 실패"
                            )
                        }
                }
                .onFailure { e ->
                    _uiState.value = _uiState.value.copy(
                        isWatering = false,
                        error = e.message ?: "물주기 실패"
                    )
                }
                .onSuccess {
                    // 히스토리도 최신화
                    loadHistories(plantId)
                }
        }
    }

    fun selectPlant(plantId: String) {
        _uiState.value = _uiState.value.copy(selectedPlantId = plantId)
    }
}
