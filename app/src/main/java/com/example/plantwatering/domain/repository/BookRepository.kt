package com.example.plantwatering.domain.repository

import com.example.plantwatering.domain.model.Book

interface BookRepository {
    suspend fun getBooks(): List<Book>
    suspend fun getBook(bookId: String): Book?
}

