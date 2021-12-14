package com.example.quotesapp.domain.usecase.fetchquotes

import com.example.quotesapp.domain.model.QuoteDomain
import kotlinx.coroutines.flow.Flow

interface FetchQuotesUseCase {
    suspend fun fetchQuotesCache(query: String): Flow<List<QuoteDomain.QuoteDomainModel>>
    suspend fun fetchQuotesCloud(): Flow<List<QuoteDomain.QuoteDomainModel>>
    suspend fun updateQuote(isCheck: Boolean, author: String)
}