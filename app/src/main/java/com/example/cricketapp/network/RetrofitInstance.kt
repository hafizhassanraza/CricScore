package com.example.cricketapp.network

import com.example.cricketapp.data.RetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private var retrofitService: RetrofitService? = null
    fun getInstance(url: String) : RetrofitService {
        if (retrofitService == null){
            val retrofit by lazy {
                Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitService::class.java)
            }
            retrofitService = retrofit
        }
        return retrofitService!!
    }
}