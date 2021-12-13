package com.example.quotesapp.data.repository

import com.example.quotesapp.data.datasource.local.QuoteCacheDataSource
import com.example.quotesapp.data.datasource.local.QuoteCacheModel
import com.example.quotesapp.data.datasource.net.QuoteCloudDataSource
import com.example.quotesapp.data.quote.mapper.QuoteDataToCacheMapperImpl
import com.example.quotesapp.data.quote.mapper.QuotesDataToDomainMapper
import com.example.quotesapp.data.quote.model.QuoteDataModel
import com.example.quotesapp.domain.ResultData
import com.example.quotesapp.domain.model.QuoteDomain
import com.example.quotesapp.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class QuoteRepositoryImpl(
    private val quoteCloudDataSource: QuoteCloudDataSource,
    private val quotesDataToDomainMapper: QuotesDataToDomainMapper,
    private val context: CoroutineContext,
    private val quoteCacheDataSource: QuoteCacheDataSource,
    private val quoteDataToCacheMapper: QuoteDataToCacheMapperImpl,
) : QuoteRepository {
    override suspend fun fetchQuotes(query: String): ResultData<List<QuoteDomain.QuoteDomainModel>> {
        return try {
            withContext(context) {
                var quotesCache: List<QuoteCacheModel> = quoteCacheDataSource.fetchQuotes(query)
                if (quotesCache.isEmpty()) {
                    val quotesData: List<QuoteDataModel> = quoteCloudDataSource.fetchQuotes()
                    quotesCache = quotesData.map {
                        it.toCacheMap(quoteDataToCacheMapper)
                    }
                    quoteCacheDataSource.insertQuotes(quotesCache)
                    val quotesDomain: List<QuoteDomain.QuoteDomainModel> =
                        quotesDataToDomainMapper.map(quotesData)
                    ResultData.Success(quotesDomain)
                } else {
                    val quoteData: List<QuoteDataModel> = quotesCache.map {
                        QuoteDataModel(it.text, it.author)
                    }
                    ResultData.Success(quotesDataToDomainMapper.map(quoteData))
                }
            }
        } catch (e: Exception) {
            ResultData.Error(e)
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
        withContext(context){
            quoteCacheDataSource.toSaveAndDelete(toSave, text)

        }
    }
}