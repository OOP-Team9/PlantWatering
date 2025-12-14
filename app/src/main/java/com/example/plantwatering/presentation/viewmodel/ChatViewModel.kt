package com.example.plantwatering.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import kotlinx.coroutines.launch

open class ChatViewModel : ViewModel() {

    var answer by mutableStateOf("")
        private set

    private val model = Firebase.ai(
        backend = GenerativeBackend.googleAI()
    ).generativeModel("gemini-2.5-flash")

    open fun sendMessage(prompt: String) {
        viewModelScope.launch {

            answer = "답변 생성 중이에요..."

            val finalPrompt = """
                너는 식물 도감 AI야.
                초보자도 이해할 수 있게 짧고 친절하게 답해줘.

                질문: $prompt
            """.trimIndent()

            val response = model.generateContent(finalPrompt)
            answer = response.text ?: "답변을 가져오지 못했어요."
        }
    }
}




