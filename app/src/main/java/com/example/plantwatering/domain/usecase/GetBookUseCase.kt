package com.example.plantwatering.domain.usecase

import com.example.plantwatering.domain.model.Book
import com.example.plantwatering.domain.repository.BookRepository

class GetBookUseCase(
    private val repo: BookRepository
) {
    suspend operator fun invoke(bookId: String = "1"): Book? = repo.getBook(bookId)
}

