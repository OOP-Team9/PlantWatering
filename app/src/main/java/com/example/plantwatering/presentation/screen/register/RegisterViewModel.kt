package com.example.plantwatering.presentation.screen.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel : ViewModel() {

    private val _plantName = MutableStateFlow("")
    val plantName: StateFlow<String> = _plantName

    private val _wateringInterval = MutableStateFlow("")
    val wateringInterval: StateFlow<String> = _wateringInterval

    private val _lastWaterDate = MutableStateFlow("")
    val lastWaterDate: StateFlow<String> = _lastWaterDate

    fun updateName(new: String) {
        _plantName.value = new
    }

    fun updateInterval(new: String) {
        _wateringInterval.value = new
    }

    fun updateLastDate(new: String) {
        _lastWaterDate.value = new
    }
}

