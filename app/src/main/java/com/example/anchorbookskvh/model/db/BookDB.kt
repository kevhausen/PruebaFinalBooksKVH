package com.example.anchorbookskvh.model.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.anchorbookskvh.model.dataclass.Book
import com.example.anchorbookskvh.model.dataclass.BookDetail

@Database(entities = [Book::class,BookDetail::class],version = 1,exportSchema = false)
abstract class BookDB : RoomDatabase() {
    abstract fun bookDao():BookDao
}

class BookApplication:Application(){
    companion object{
        var bookDB:BookDB?=null
    }

    override fun onCreate() {
        super.onCreate()
        bookDB= Room.databaseBuilder(this,BookDB::class.java,"books_database").build()
    }
}
