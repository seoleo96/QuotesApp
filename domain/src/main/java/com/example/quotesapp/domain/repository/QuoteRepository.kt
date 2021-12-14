package com.example.quotesapp.domain.repository

import com.example.quotesapp.domain.ResultData
import com.example.quotesapp.domain.model.QuoteDomain
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    suspend fun fetchQuotesCloud(): Flow<List<QuoteDomain.QuoteDomainModel>>
    suspend fun fetchQuotesCache(query : String): Flow<List<QuoteDomain.QuoteDomainModel>>
    suspend fun updateQuote(isCheck: Boolean, author: String)
    fun fetchFavourites() : Flow<List<QuoteDomain.QuoteDomainModel>>
    suspend fun toSaveAndDelete(toSave: Boolean, text: String)
}