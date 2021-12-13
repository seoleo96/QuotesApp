package com.example.quotesapp.domain.usecase.fetchquotes

import com.example.quotesapp.domain.ResultData
import com.example.quotesapp.domain.model.AuthorDomain
import com.example.quotesapp.domain.model.QuoteDomain

interface FetchQuotesUseCase {
    suspend fun fetchQuotes(query: String): ResultData<List<QuoteDomain.QuoteDomainModel>>
    suspend fun updateQuote(isCheck: Boolean, author: String)
}