package com.example.plantwatering.domain.repository

import android.net.Uri

interface PhotoRepository {
    /**
     * 업로드 후 downloadUrl 문자열 반환
     */
    suspend fun upload(uri: Uri): String
}

