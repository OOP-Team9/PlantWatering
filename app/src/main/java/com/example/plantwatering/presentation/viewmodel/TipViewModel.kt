package com.example.plantwatering.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantwatering.domain.usecase.GetTipUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class TipUiState(
    val isLoading: Boolean = false,
    val tip: String? = null,
    val error: String? = null
)

class TipViewModel(
    private val getTipUseCase: GetTipUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TipUiState())
    val uiState: StateFlow<TipUiState> = _uiState.asStateFlow()

    fun loadTip(tipId: String = "default") {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val tip = getTipUseCase(tipId)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    tip = tip?.content
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "팁을 불러오지 못했어요"
                )
            }
        }
    }
}

