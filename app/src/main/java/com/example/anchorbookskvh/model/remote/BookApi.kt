package com.example.anchorbookskvh.model.remote

import com.example.anchorbookskvh.model.dataclass.Book
import com.example.anchorbookskvh.model.dataclass.BookDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookApi {

    @GET("books")
    suspend fun retrieveBooks():Response<List<Book>>

    @GET("bookDetail/{id}")
    suspend fun retrieveBookDetail(@Path("id")id:Int): Response<BookDetail>
}