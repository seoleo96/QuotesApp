package com.example.quotesapp.domain.usecase.fetchquotes

import com.example.quotesapp.domain.model.QuoteDomain
import com.example.quotesapp.domain.repository.QuoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FetchQuotesUseCaseImpl(
    private val quotesRepository: QuoteRepository,
    private val context: CoroutineDispatcher,
) : FetchQuotesUseCase {
    override suspend fun fetchQuotesCache(query: String): Flow<List<QuoteDomain.QuoteDomainModel>> {
        return withContext(context) {
            val data = quotesRepository.fetchQuotesCache(query)
            data
        }
    }

    override suspend fun fetchQuotesCloud(): Flow<List<QuoteDomain.QuoteDomainModel>> {
        return withContext(context) {
            quotesRepository.fetchQuotesCloud()
        }
    }

    override suspend fun updateQuote(isCheck: Boolean, author: String) {
        quotesRepository.updateQuote(isCheck, author)
    }
}