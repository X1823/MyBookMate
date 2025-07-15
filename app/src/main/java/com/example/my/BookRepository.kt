package com.example.my

import androidx.lifecycle.LiveData

class BookRepository(private val bookDao: BookDao) {
    fun insertBook(book: Book) {
        bookDao.insertBook(book)
    }

    fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }

    fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }

    fun clearBooks() {
        bookDao.clearBooks()
    }

    fun getAllBooks(): LiveData<List<Book>> {
        return bookDao.getAllBooks()
    }
}
