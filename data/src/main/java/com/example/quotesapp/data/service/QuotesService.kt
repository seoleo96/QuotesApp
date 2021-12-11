package com.example.quotesapp.data.service

import com.example.quotesapp.data.quote.model.QuoteDataModel
import retrofit2.http.GET

interface QuotesService {

    @GET("quotes")
    suspend fun fetchQuotes(): List<QuoteDataModel>
}