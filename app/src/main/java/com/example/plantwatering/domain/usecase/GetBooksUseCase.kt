package com.example.plantwatering.domain.usecase

import com.example.plantwatering.domain.model.Book
import com.example.plantwatering.domain.repository.BookRepository

class GetBooksUseCase(
    private val repo: BookRepository
) {
    suspend operator fun invoke(): List<Book> = repo.getBooks()
}

