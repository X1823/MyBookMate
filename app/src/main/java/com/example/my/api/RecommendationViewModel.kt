package com.example.my.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecommendationViewModel : ViewModel() {
    private val repository = RecommendationRepository()
    private val _books = MutableStateFlow<List<RecommendedBook>>(emptyList())
    val books: StateFlow<List<RecommendedBook>> = _books

    fun fetchBooks() {
        val keywords = listOf("reading", "classic", "fiction", "poetry", "history", "adventure", "science", "literature")
        val randomQuery = keywords.random()
        viewModelScope.launch {
            try {
                _books.value = repository.getRecommendations(randomQuery).take(10)
            } catch (e: Exception) {
                _books.value = emptyList()
            }
        }
    }

}
