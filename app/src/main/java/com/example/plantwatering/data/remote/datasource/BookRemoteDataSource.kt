package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.BookDto

interface BookRemoteDataSource {
    suspend fun getBooks(): List<BookDto> //도감 목록 전체
    //비동기
    suspend fun getBook(bookId: String): BookDto? //1개
}

