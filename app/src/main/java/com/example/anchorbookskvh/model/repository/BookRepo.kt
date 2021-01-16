package com.example.anchorbookskvh.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.anchorbookskvh.model.dataclass.Book
import com.example.anchorbookskvh.model.dataclass.BookDetail
import com.example.anchorbookskvh.model.db.BookApplication
import com.example.anchorbookskvh.model.db.BookDB
import com.example.anchorbookskvh.model.remote.RetrofitBook
import okhttp3.ResponseBody

class BookRepo {

    private val retrofit=RetrofitBook.retrofitInstance()
    private val dao=BookApplication.bookDB!!.bookDao()

    //se guarda aca el mensaje de error para luego llamarlo en la vista
    var errorMessage=MutableLiveData<ResponseBody>()

    suspend fun setBooksWebIntoDB(){
        val response = retrofit.retrieveBooks()
        when(response.isSuccessful){
            true-> response.body()?.let { dao.insertBooks(it) }
            false->errorMessage.value=response.errorBody()
        }
    }

    suspend fun setBookDetailIntoDB(id:Int){
        val response=retrofit.retrieveBookDetail(id)
        when(response.isSuccessful){
            true-> response.body()?.let { dao.insertBookDetail(it) }
            false-> errorMessage.value=response.errorBody()
        }
    }

    fun getBookList():LiveData<List<Book>>{
        return dao.getBookList()
    }

    fun getBookDetail(id:Int):LiveData<BookDetail>{
        return dao.getBoomDetailById(id)
    }
}