package com.example.quotesapp.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private companion object {
        val BASE_URL: String = "https://type.fit/api/"
    }

    fun instance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun retrofitService(retrofit: Retrofit): QuotesService {
        return retrofit.create(QuotesService::class.java)
    }
}