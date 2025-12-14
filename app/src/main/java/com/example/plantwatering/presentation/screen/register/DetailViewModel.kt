package com.example.plantwatering.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel : ViewModel() {

    // 상단 카드용
    private val _plantName = MutableStateFlow("로즈마리")
    val plantName: StateFlow<String> = _plantName

    private val _dDay = MutableStateFlow("D-DAY") // 실제 계산은 나중에
    val dDay: StateFlow<String> = _dDay

    // 수정 가능한 입력값들
    private val _wateringInterval = MutableStateFlow("7")
    val wateringInterval: StateFlow<String> = _wateringInterval

    private val _lastWaterDate = MutableStateFlow("2025.12.12")
    val lastWaterDate: StateFlow<String> = _lastWaterDate

    private val _species = MutableStateFlow("선택 안함")
    val species: StateFlow<String> = _species

    private val _diary = MutableStateFlow("")
    val diary: StateFlow<String> = _diary

    fun updateInterval(new: String) {
        _wateringInterval.value = new
    }

    fun updateLastDate(new: String) {
        _lastWaterDate.value = new
    }

    fun updateSpecies(new: String) {
        _species.value = new
    }

    fun updateDiary(new: String) {
        _diary.value = new
    }
}

