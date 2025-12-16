package com.example.plantwatering.domain.repository

import com.example.plantwatering.domain.model.Book

interface BookRepository {
    suspend fun getBookByPlantName(plantName: String): Book?
}

