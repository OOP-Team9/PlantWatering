package com.example.plantwatering.data.repository

import com.example.plantwatering.data.mapper.toDomain
import com.example.plantwatering.data.remote.datasource.BookRemoteDataSource
import com.example.plantwatering.domain.model.Book
import com.example.plantwatering.domain.repository.BookRepository

class BookRepositoryImpl(
    private val bookDs: BookRemoteDataSource
) : BookRepository {

    override suspend fun getBooks(): List<Book> =
        bookDs.getBooks().mapNotNull { it.toDomain() }

    override suspend fun getBook(bookId: String): Book? =
        bookDs.getBook(bookId)?.toDomain()
}

