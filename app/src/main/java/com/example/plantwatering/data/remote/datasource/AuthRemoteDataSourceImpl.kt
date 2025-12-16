package com.example.plantwatering.data.remote.datasource

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRemoteDataSourceImpl(
    private val auth: FirebaseAuth
) : AuthRemoteDataSource {

    override suspend fun ensureSignedIn(): String {
        // 이미 로그인(익명 포함) 되어 있으면 그대로 uid 사용
        auth.currentUser?.uid?.let { return it }

        // 아니면 익명 로그인
        val result = auth.signInAnonymously().await()
        return result.user?.uid ?: throw IllegalStateException("Anonymous sign-in failed")
    }
}
