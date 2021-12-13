package com.example.quotesapp.data.datasource.local

import android.content.Context
import kotlinx.coroutines.flow.Flow


class QuoteCacheDataSourceImpl(
    context: Context,
) : QuoteCacheDataSource {

    private val quoteDao = QuoteDB.getDatabase(context).quoteDao()
    override suspend fun fetchQuotes(query: String) = quoteDao.fetchQuotes(query)

    override suspend fun insertQuotes(quotes: List<QuoteCacheModel>) {
        quoteDao.deleteAll()
        quoteDao.insertAll(quotes)
    }

    override suspend fun fetchAuthors() = quoteDao.fetchAuthors()
    override suspend fun fetchAuthorsByQuery(query: String): List<QuoteCacheModel> {
        return quoteDao.fetchAuthorsByQuery(query)
    }

    override suspend fun updateQuote(isCheck: Boolean, author: String) {
        quoteDao.updateQuote(isCheck, author)
    }

    override  fun fetchFavourites(): Flow<List<QuoteCacheModel>> {
        return quoteDao.fetchFavourites()
    }

    override suspend fun toSaveAndDelete(toSave: Boolean, text: String): Int {
        return quoteDao.toSaveAndDelete(toSave, text)
    }
}