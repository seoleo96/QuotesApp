package com.example.quotesapp.data.datasource.local

import kotlinx.coroutines.flow.Flow


interface QuoteCacheDataSource {

    fun fetchQuotes(query : String): Flow<List<QuoteCacheModel>>
    fun fetchQuotesCheck(): List<QuoteCacheModel>
    suspend fun insertQuotes(quotes: List<QuoteCacheModel>)
    suspend fun fetchAuthors(): List<QuoteCacheModel>
    suspend fun fetchAuthorsByQuery(query : String): List<QuoteCacheModel>
    suspend fun updateQuote(isCheck: Boolean, author: String)
    fun fetchFavourites() : Flow<List<QuoteCacheModel>>
    suspend fun toSaveAndDelete(toSave: Boolean, text: String) : Int
}