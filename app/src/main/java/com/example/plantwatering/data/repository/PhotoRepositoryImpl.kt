package com.example.plantwatering.data.repository

import android.net.Uri
import com.example.plantwatering.domain.repository.PhotoRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.util.UUID

class PhotoRepositoryImpl(
    private val storage: FirebaseStorage
) : PhotoRepository {

    override suspend fun upload(uri: Uri): String {
        val ref = storage.reference.child("photos/${UUID.randomUUID()}.jpg")
        ref.putFile(uri).await()
        return ref.downloadUrl.await().toString()
    }
}

