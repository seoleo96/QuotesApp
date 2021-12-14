package com.example.quotesapp.data.datasource.local

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow


class QuoteCacheDataSourceImpl(
    context: Context,
) : QuoteCacheDataSource {

    private val quoteDao = QuoteDB.getDatabase(context).quoteDao()
    override fun fetchQuotes(query: String): Flow<List<QuoteCacheModel>> =
        quoteDao.fetchQuotes(query)

    override fun fetchQuotesCheck(): List<QuoteCacheModel> {
        return quoteDao.fetchQuotesCheck()
    }

    override suspend fun insertQuotes(quotes: List<QuoteCacheModel>) {
        quoteDao.deleteAll()
        quoteDao.insertAll(quotes)
    }

    override suspend fun fetchAuthors() = quoteDao.fetchAuthors()
    override suspend fun fetchAuthorsByQuery(query: String): List<QuoteCacheModel> {
        return quoteDao.fetchAuthorsByQuery(query)
    }

    override suspend fun updateQuote(isCheck: Boolean, author: String) {
        quoteDao.updateAll(false, true)
        quoteDao.updateQuote(isCheck, author)
    }

    override fun fetchFavourites(): Flow<List<QuoteCacheModel>> {
        return quoteDao.fetchFavourites()
    }

    override suspend fun toSaveAndDelete(toSave: Boolean, text: String): Int {
        return quoteDao.toSaveAndDelete(toSave, text)
    }
}