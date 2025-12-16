package com.example.plantwatering.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantwatering.data.remote.datasource.AuthRemoteDataSourceImpl
import com.example.plantwatering.data.remote.datasource.PlantRemoteDataSourceImpl
import com.example.plantwatering.data.repository.PlantRepositoryImpl
import com.example.plantwatering.domain.usecase.GetPlantsUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {

            /** Firebase */
            val firestore = FirebaseFirestore.getInstance()
            val auth = FirebaseAuth.getInstance()

            /** DataSource */
            val authDs = AuthRemoteDataSourceImpl(auth)
            val plantDs = PlantRemoteDataSourceImpl(firestore)

            /** Repository */
            val plantRepo = PlantRepositoryImpl(
                authDs = authDs,
                plantDs = plantDs
            )

            /** UseCase */
            val getPlantsUseCase = GetPlantsUseCase(plantRepo)

            return HomeViewModel(
                getPlantsUseCase = getPlantsUseCase
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
