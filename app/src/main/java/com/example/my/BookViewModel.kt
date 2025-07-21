package com.example.my

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    val allBooks: LiveData<List<Book>> = repository.allBooks

    fun addBook(book: Book) {
        viewModelScope.launch {
            repository.insert(book)
        }
    }

    fun updateBook(book: Book) {
        viewModelScope.launch {
            repository.update(book)
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            repository.delete(book)
        }
    }

    fun deleteAllBooks() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}
