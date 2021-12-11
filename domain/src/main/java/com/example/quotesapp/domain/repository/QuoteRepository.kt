package com.example.quotesapp.domain.repository

import com.example.quotesapp.domain.Result
import com.example.quotesapp.domain.model.QuoteDomain

interface QuoteRepository {

    suspend fun fetchQuotes(): Result<List<QuoteDomain.QuoteDomainModel>>
}