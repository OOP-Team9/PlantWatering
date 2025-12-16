package com.example.plantwatering.data.remote.datasource

interface AuthRemoteDataSource {
    suspend fun ensureSignedIn(): String // uid 반환
}
