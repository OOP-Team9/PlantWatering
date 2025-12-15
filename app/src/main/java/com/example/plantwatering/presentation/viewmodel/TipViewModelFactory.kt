package com.example.plantwatering.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantwatering.data.remote.datasource.TipRemoteDataSourceImpl
import com.example.plantwatering.data.repository.TipRepositoryImpl
import com.example.plantwatering.domain.usecase.GetTipUseCase
import com.google.firebase.firestore.FirebaseFirestore

class TipViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val db = FirebaseFirestore.getInstance()
        val tipDs = TipRemoteDataSourceImpl(db)
        val repo = TipRepositoryImpl(tipDs)
        val useCase = GetTipUseCase(repo)
        return TipViewModel(useCase) as T
    }
}

