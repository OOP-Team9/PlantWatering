package com.example.plantwatering.data.repository

import com.example.plantwatering.data.remote.datasource.BookRemoteDataSource
import com.example.plantwatering.data.remote.dto.toDomain
import com.example.plantwatering.domain.model.Book
import com.example.plantwatering.domain.repository.BookRepository

class BookRepositoryImpl(
    private val bookDs: BookRemoteDataSource
) : BookRepository {

    override suspend fun getBookByPlantName(plantName: String): Book? {
        return bookDs.getBooks()
            .firstOrNull { it.plantName == plantName }
            ?.toDomain()
    }
}

