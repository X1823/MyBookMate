package com.example.my

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book)

    @Update
    fun updateBook(book: Book)

    @Delete
    fun deleteBook(book: Book)

    @Query("DELETE FROM book_table")
    fun clearBooks()

    @Query("SELECT * FROM book_table ORDER BY id DESC")
    fun getAllBooks(): LiveData<List<Book>>
}

