package com.example.anchorbookskvh.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.anchorbookskvh.model.dataclass.Book
import com.example.anchorbookskvh.model.dataclass.BookDetail

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(bookList: List<Book>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookDetail(detail: BookDetail)

    @Query("SELECT * FROM books")
    fun getBookList(): LiveData<List<Book>>

    @Query("SELECT * FROM books_detail WHERE id=:id")
    fun getBoomDetailById(id: Int): LiveData<BookDetail>


}