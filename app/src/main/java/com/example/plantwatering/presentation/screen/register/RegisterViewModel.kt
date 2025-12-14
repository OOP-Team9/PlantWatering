package com.example.plantwatering.presentation.screen.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel : ViewModel() {

    // 식물 이름
    private val _plantName = MutableStateFlow("")
    val plantName: StateFlow<String> = _plantName

    // 물 주기 간격
    private val _wateringInterval = MutableStateFlow("")
    val wateringInterval: StateFlow<String> = _wateringInterval

    // 마지막 급수일
    private val _lastWaterDate = MutableStateFlow("")
    val lastWaterDate: StateFlow<String> = _lastWaterDate

    // 식물 종
    private val _species = MutableStateFlow("선택 안함")
    val species: StateFlow<String> = _species

    // 사진 URL
    private val _photoUrl = MutableStateFlow("")
    val photoUrl: StateFlow<String> = _photoUrl

    fun updateName(new: String) {
        _plantName.value = new
    }

    fun updateInterval(new: String) {
        _wateringInterval.value = new
    }

    fun updateLastDate(new: String) {
        _lastWaterDate.value = new
    }

    fun updateSpecies(new: String) {
        _species.value = new
    }

    fun updatePhotoUrl(new: String) {
        _photoUrl.value = new
    }
}

