package com.example.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    val allBooks: LiveData<List<Book>> = repository.getAllBooks()

    fun insertBook(title: String, author: String, progress: Int, copy: String, note: String) {
        viewModelScope.launch {
            val book = Book(title = title, author = author, progress = progress, copy = copy, note = note)
            repository.insertBook(book)
        }
    }

    fun updateBook(book: Book) {
        viewModelScope.launch {
            repository.updateBook(book)
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            repository.deleteBook(book)
        }
    }

    fun clearBooks() {
        viewModelScope.launch {
            repository.clearBooks()
        }
    }
}
