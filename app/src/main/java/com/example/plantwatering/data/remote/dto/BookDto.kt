package com.example.plantwatering.data.remote.dto

data class BookDto(
    val bookId: String = "",          // 문서 ID
    val plantName: String = "",       // 식물 이름
    val plantEngName: String = "",    // 식물 영어 이름
    val imageUrl: String = "",        // 사진 URL
    val lightInfo: String = "",       // 빛 정보
    val waterInfo: String = "",       // 물 정보
    val humidityInfo: String = "",    // 습도 정보
    val description: String = ""      // 설명/팁
)
