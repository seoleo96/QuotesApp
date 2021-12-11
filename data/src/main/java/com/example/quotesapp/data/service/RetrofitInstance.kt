package com.example.quotesapp.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private companion object {
        val BASE_URL: String = "https://type.fit/api/"
    }

    private val retofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val quoteService: QuotesService = retofit.create(QuotesService::class.java)
}