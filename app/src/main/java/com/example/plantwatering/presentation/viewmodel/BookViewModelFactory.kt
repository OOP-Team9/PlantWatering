package com.example.plantwatering.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantwatering.data.remote.datasource.BookRemoteDataSourceImpl
import com.example.plantwatering.data.repository.BookRepositoryImpl
import com.example.plantwatering.domain.usecase.GetBookUseCase
import com.google.firebase.firestore.FirebaseFirestore

class BookViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {

            val db = FirebaseFirestore.getInstance()

            val bookRemoteDataSource = BookRemoteDataSourceImpl(db)

            val bookRepository = BookRepositoryImpl(bookRemoteDataSource)

            val getBookUseCase = GetBookUseCase(bookRepository)

            @Suppress("UNCHECKED_CAST")
            return BookViewModel(getBookUseCase) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
