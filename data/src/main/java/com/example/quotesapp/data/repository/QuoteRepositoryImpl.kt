package com.example.quotesapp.data.repository

import com.example.quotesapp.data.datasource.local.QuoteCacheDataSource
import com.example.quotesapp.data.datasource.local.QuoteCacheModel
import com.example.quotesapp.data.datasource.net.QuoteCloudDataSource
import com.example.quotesapp.data.quote.mapper.QuoteDataToCacheMapper
import com.example.quotesapp.data.quote.mapper.QuotesDataToDomainMapper
import com.example.quotesapp.data.quote.model.QuoteDataModel
import com.example.quotesapp.domain.model.QuoteDomain
import com.example.quotesapp.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class QuoteRepositoryImpl(
    private val quoteCloudDataSource: QuoteCloudDataSource,
    private val quotesDataToDomainMapper: QuotesDataToDomainMapper,
    private val context: CoroutineContext,
    private val quoteCacheDataSource: QuoteCacheDataSource,
    private val quoteDataToCacheMapper: QuoteDataToCacheMapper,
) : QuoteRepository {
    override suspend fun fetchQuotesCloud(): Flow<List<QuoteDomain.QuoteDomainModel>> {
        val quotesData: List<QuoteDataModel> = quoteCloudDataSource.fetchQuotes()
        val quoteCache: List<QuoteCacheModel> =
            quotesData.map { it.toCacheMap(quoteDataToCacheMapper) }
        quoteCacheDataSource.insertQuotes(quoteCache)
        val quotesDoamin: List<QuoteDomain.QuoteDomainModel> =
            quotesDataToDomainMapper.map(quotesData)
        return flow { emit(quotesDoamin) }.flowOn(context)
    }

    override suspend fun fetchQuotesCache(query: String): Flow<List<QuoteDomain.QuoteDomainModel>> {
        val quotesCache: Flow<List<QuoteCacheModel>> = quoteCacheDataSource.fetchQuotes(query)
        return quotesCache.map { list ->
            list.map {
                QuoteDomain.QuoteDomainModel(it.text, it.author, it.toSave)
            }
        }
    }

    override suspend fun updateQuote(isCheck: Boolean, author: String) {
        quoteCacheDataSource.updateQuote(isCheck, author)
    }

    override fun fetchFavourites(): Flow<List<QuoteDomain.QuoteDomainModel>> {
        return quoteCacheDataSource.fetchFavourites().map { list ->
            list.map {
                QuoteDomain.QuoteDomainModel(it.text, it.author, it.toSave)
            }
        }
    }

    override suspend fun toSaveAndDelete(toSave: Boolean, text: String) {
        withContext(context) {
            quoteCacheDataSource.toSaveAndDelete(toSave, text)

        }
    }
}