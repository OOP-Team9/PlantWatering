package com.example.plantwatering.data.remote.dto

/**
 * 식물 팁 문서 모델.
 * - collection: /tips/{tipId}
 */
data class TipDto(
    val tipId: String = "",   // 문서 ID (string)
    val content: String = ""  // 내용
)

