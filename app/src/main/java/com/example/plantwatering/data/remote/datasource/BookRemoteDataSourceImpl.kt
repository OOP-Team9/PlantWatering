package com.example.plantwatering.data.remote.datasource

import com.example.plantwatering.data.remote.dto.BookDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class BookRemoteDataSourceImpl(
    private val db: FirebaseFirestore
) : BookRemoteDataSource {
    private fun booksCol() = db.collection("books") //Firestore의 books 컬렉션을 가져오기
    override suspend fun getBooks(): List<BookDto> {
        val snap = booksCol() //books 컬렉션 참조 얻기
            .orderBy("plantName") //문서들을 plantName 필드 기준으로 정렬해서 가져옴
            .get() //요청
            .await()

        return snap.documents.mapNotNull { doc -> //firestore 문서들 BookDto로 변환 + id를 bookId 필드에 넣기
            //null이면 버림
            doc.toObject(BookDto::class.java)?.copy(bookId = doc.id)
        }
    }

    override suspend fun getBook(bookId: String): BookDto? {
        val doc = booksCol().document(bookId).get().await()
        return doc.toObject(BookDto::class.java)?.copy(bookId = doc.id)
    }
}

