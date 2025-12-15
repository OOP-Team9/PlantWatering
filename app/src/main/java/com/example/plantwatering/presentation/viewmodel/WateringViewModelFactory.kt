package com.example.plantwatering.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantwatering.data.remote.datasource.AuthRemoteDataSourceImpl
import com.example.plantwatering.data.remote.datasource.HistoryRemoteDataSourceImpl
import com.example.plantwatering.data.remote.datasource.PlantRemoteDataSourceImpl
import com.example.plantwatering.data.remote.datasource.WateringRemoteDataSourceImpl
import com.example.plantwatering.data.repository.PlantRepositoryImpl
import com.example.plantwatering.data.repository.HistoryRepositoryImpl
import com.example.plantwatering.data.repository.WateringRepositoryImpl
import com.example.plantwatering.domain.usecase.GetPlantsUseCase
import com.example.plantwatering.domain.usecase.GetHistoriesUseCase
import com.example.plantwatering.domain.usecase.WaterPlantUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class WateringViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val db = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()

        val authDs = AuthRemoteDataSourceImpl(auth)
        val plantDs = PlantRemoteDataSourceImpl(db)
        val wateringDs = WateringRemoteDataSourceImpl(db)
        val historyDs = HistoryRemoteDataSourceImpl(db)

        val plantRepo = PlantRepositoryImpl(authDs, plantDs)
        val wateringRepo = WateringRepositoryImpl(authDs, plantDs, wateringDs)
        val historyRepo = HistoryRepositoryImpl(authDs, historyDs)

        val getPlants = GetPlantsUseCase(plantRepo)
        val getHistories = GetHistoriesUseCase(historyRepo)
        val waterPlant = WaterPlantUseCase(wateringRepo)

        return WateringViewModel(getPlants, waterPlant, getHistories) as T
    }
}
