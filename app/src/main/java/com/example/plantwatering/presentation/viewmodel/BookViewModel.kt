package com.example.plantwatering.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantwatering.domain.model.Book
import com.example.plantwatering.domain.usecase.GetBookUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class BookUiState(
    val isLoading: Boolean = false,
    val book: Book? = null,
    val error: String? = null
)

class BookViewModel(
    private val getBookUseCase: GetBookUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    fun searchBook(bookId: String) {
        viewModelScope.launch {
            _uiState.value = BookUiState(isLoading = true)

            runCatching { getBookUseCase(bookId) }
                .onSuccess { book ->
                    _uiState.value = BookUiState(book = book)
                }
                .onFailure {e ->
                    _uiState.value = BookUiState(
                        isLoading = false,
                        error = e.message ?: "식물 정보를 불러오지 못했어요"
                    )
                }
        }
    }
}