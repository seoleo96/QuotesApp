package com.example.quotesapp.domain.usecase.fetchquotes

import com.example.quotesapp.domain.model.QuoteDomain
import com.example.quotesapp.domain.repository.QuoteRepository
import com.example.quotesapp.domain.ResultData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FetchQuotesUseCaseImpl(
    private val quotesRepository: QuoteRepository,
    private val context: CoroutineDispatcher,
) : FetchQuotesUseCase {
    override suspend fun fetchQuotes(query: String): ResultData<List<QuoteDomain.QuoteDomainModel>> {
        return withContext(context) {
           val data =  quotesRepository.fetchQuotes(query)
            data
        }
    }

    override suspend fun updateQuote(isCheck: Boolean, author: String) {
        quotesRepository.updateQuote(isCheck, author)
    }
}