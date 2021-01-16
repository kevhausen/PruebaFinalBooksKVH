package com.example.anchorbookskvh

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.anchorbookskvh.model.dataclass.Book
import com.example.anchorbookskvh.model.db.BookDB
import com.example.anchorbookskvh.model.db.BookDao
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestDao {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var bookDao: BookDao
    private lateinit var db: BookDB

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, BookDB::class.java).build()
        bookDao = db.bookDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertBook() = runBlocking {
        //given
        val bookList = listOf(Book("Miguel de Cervantes","Spain",2,"www.wikipedia.com/22234234.jpg","spanish","Don Quijote"))

        // when
        bookDao.insertBooks(bookList)

        //then
        bookDao.getBookList().observeForever{
            assertThat(it).isNotNull()
            println(it.toString())
            assertThat(it[0].author).isEqualTo("Miguel de Cervantes")

        }
    }
}