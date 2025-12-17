package com.example.plantwatering.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantwatering.domain.model.Plant
import com.example.plantwatering.domain.model.WateringHistory
import com.example.plantwatering.domain.usecase.GetPlantsUseCase
import com.example.plantwatering.domain.usecase.GetHistoriesUseCase
import com.example.plantwatering.domain.usecase.WaterPlantUseCase
import android.util.Log
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

    init {
        loadPlants()
    }

    fun loadPlants() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val plantList = getPlantsUseCase().sortedBy { it.nextWateringAt }
                val selectedPlantId = _uiState.value.selectedPlantId ?: plantList.firstOrNull()?.plantId
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    plants = plantList,
                    selectedPlantId =selectedPlantId
                )
                //selectedPlantId?.let { loadHistories(it) } //null이 아니면 loadHistories 실행
            } catch (e: Exception) {
                Log.e("PlantLoad", "fail", e)
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
            try {
                val list = getHistoriesUseCase(plantId)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    histories = list,
                    selectedPlantId = plantId
                )
            } catch (e: Exception) {
                Log.e("HistoryLoad", "fail", e)
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
            try {
                waterPlantUseCase(plantId)

                // 식물 갱신
                // 바뀐 식물 목록들 다시 가지고 오기
                val list = getPlantsUseCase()
                _uiState.value = _uiState.value.copy(
                    isWatering = false,
                    plants = list,
                    selectedPlantId = plantId
                )

                // 히스토리 갱신
                loadHistories(plantId)
            } catch (e: Exception) {
                Log.e("WaterPlant", "fail", e)
                _uiState.value = _uiState.value.copy(
                    isWatering = false,
                    error = e.message ?: "물주기 실패"
                )
            }
        }
    }

    fun selectPlant(plantId: String) {
        _uiState.value = _uiState.value.copy(selectedPlantId = plantId)
    }
}
