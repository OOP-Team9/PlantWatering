package com.example.plantwatering.data.remote.dto

/**
 * 식물 도감(Book) 문서 모델.
 * - collection: /books/{bookId}
 */
data class BookDto(
    val bookId: String = "",          // 문서 ID (string)
    val plantName: String = "",       // 식물 이름
    val plantEngName: String = "",    // 식물 영어 이름
    val photoUri: String = "",        // 사진 URL
    val lightInfo: String = "",       // 빛 정보
    val waterInfo: String = "",       // 물 정보
    val humidityInfo: String = "",    // 습도 정보
    val description: String = ""      // 설명/팁
)
