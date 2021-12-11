package com.example.quotesapp.data.datasource.net

import com.example.quotesapp.data.quote.model.QuoteDataModel

interface QuoteCloudDataSource {

    suspend fun fetchQuotes() : List<QuoteDataModel>
}