package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.BookDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class BookRemoteDataSourceImpl(
    private val db: FirebaseFirestore
) : BookRemoteDataSource {

    private fun booksCol() = db.collection("books")

    override suspend fun getBooks(): List<BookDto> {
        val snap = booksCol()
            .orderBy("plantName")
            .get()
            .await()

        return snap.documents.mapNotNull { doc ->
            doc.toObject(BookDto::class.java)?.copy(bookId = doc.id)
        }
    }

    override suspend fun getBook(bookId: String): BookDto? {
        val doc = booksCol().document(bookId).get().await()
        return doc.toObject(BookDto::class.java)?.copy(bookId = doc.id)
    }
}

