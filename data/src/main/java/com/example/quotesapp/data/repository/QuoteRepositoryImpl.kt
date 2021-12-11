package com.example.quotesapp.data.repository

import com.example.quotesapp.data.quote.mapper.QuotesDataToDomainMapper
import com.example.quotesapp.data.datasource.net.QuoteCloudDataSource
import com.example.quotesapp.data.quote.model.QuoteDataModel
import com.example.quotesapp.domain.model.QuoteDomain
import com.example.quotesapp.domain.repository.QuoteRepository
import com.example.quotesapp.domain.Result
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class QuoteRepositoryImpl(
    private val quoteCloudDataSource: QuoteCloudDataSource,
    private val quotesDataToDomainMapper: QuotesDataToDomainMapper,
    private val context: CoroutineContext,
) : QuoteRepository {
    override suspend fun fetchQuotes(): Result<List<QuoteDomain.QuoteDomainModel>> {
        return try {
            withContext(context) {
                val quotesData: List<QuoteDataModel> = quoteCloudDataSource.fetchQuotes()
                val quotesDomain: List<QuoteDomain.QuoteDomainModel> =
                    quotesDataToDomainMapper.map(quotesData)
                Result.Success(quotesDomain)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}