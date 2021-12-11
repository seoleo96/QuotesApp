package com.example.quotesapp.domain.usecase.fetchquotes

import com.example.quotesapp.domain.model.QuoteDomain
import com.example.quotesapp.domain.Result

interface FetchQuotesUseCase {
    suspend fun fetchQuotes() : Result<List<QuoteDomain.QuoteDomainModel>>
}