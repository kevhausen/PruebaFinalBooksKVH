package com.example.anchorbookskvh.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBook {

    companion object {
        private const val BASE_URL = "https://my-json-server.typicode.com/Himuravidal/anchorBooks/"

        fun retrofitInstance(): BookApi {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(BookApi::class.java)

        }
    }


}