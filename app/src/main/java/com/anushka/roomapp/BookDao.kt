package com.anushka.roomapp

import androidx.room.*

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBook(book: Book)

    @Query("SELECT * FROM books_table")
    fun getAllBooks(): List<Book>

    @Query("DELETE FROM books_table")
    suspend fun hapusSemuaBarisData()

    @Update
    suspend fun updateBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)
}