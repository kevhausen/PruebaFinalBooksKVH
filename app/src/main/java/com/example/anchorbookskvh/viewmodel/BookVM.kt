package com.example.anchorbookskvh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anchorbookskvh.model.dataclass.Book
import com.example.anchorbookskvh.model.dataclass.BookDetail
import com.example.anchorbookskvh.model.repository.BookRepo
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class BookVM : ViewModel() {

    private val repository = BookRepo()


    fun setBooksWebIntoDB() {
        viewModelScope.launch {
            repository.setBooksWebIntoDB()
        }
    }

    fun setBookDetailIntoDB(id: Int) {
        viewModelScope.launch {
            repository.setBookDetailIntoDB(id)
        }
    }

    fun getBookList(): LiveData<List<Book>> {
        return repository.getBookList()
    }

    fun getBookDetail(id: Int): LiveData<BookDetail> {
        return repository.getBookDetail(id)
    }

    fun getErrorMessage(): LiveData<ResponseBody> {
        return repository.errorMessage
    }

}