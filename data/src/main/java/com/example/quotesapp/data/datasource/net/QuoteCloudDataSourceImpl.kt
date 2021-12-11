package com.example.quotesapp.data.datasource.net

import com.example.quotesapp.data.quote.model.QuoteDataModel
import com.example.quotesapp.data.service.QuotesService

class QuoteCloudDataSourceImpl(
    private val service: QuotesService
) : QuoteCloudDataSource {
    override suspend fun fetchQuotes(): List<QuoteDataModel> {
        return service.fetchQuotes()
    }
}