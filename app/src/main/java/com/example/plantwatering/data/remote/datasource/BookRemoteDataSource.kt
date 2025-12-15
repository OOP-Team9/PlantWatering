package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.BookDto

interface BookRemoteDataSource {
    suspend fun getBooks(): List<BookDto>
    suspend fun getBook(bookId: String): BookDto?
}

