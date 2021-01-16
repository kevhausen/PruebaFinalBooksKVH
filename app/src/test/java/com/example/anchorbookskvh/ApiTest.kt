package com.example.anchorbookskvh

import com.example.anchorbookskvh.model.dataclass.Book
import com.example.anchorbookskvh.model.remote.BookApi
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {
    lateinit var mMockWebServer: MockWebServer
    lateinit var bookAPI : BookApi

    @Before
    fun setUp() {
        mMockWebServer = MockWebServer()
        val retro = Retrofit.Builder()
            .baseUrl(mMockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        bookAPI =  retro.create(BookApi::class.java)
    }

    @After
    fun shutDown() {
        mMockWebServer.shutdown()
    }

    @Test
    fun getBooks() = runBlocking {
        //given
        val mResultList = listOf(Book("Miguel de Cervantes","Spain",2,"www.wikipedia.com/22234234.jpg","spanish","Don Quijote"))
        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(mResultList)))

        //when
        val result = bookAPI.retrieveBooks()

        //then
        assertThat(result).isNotNull()


    }
}