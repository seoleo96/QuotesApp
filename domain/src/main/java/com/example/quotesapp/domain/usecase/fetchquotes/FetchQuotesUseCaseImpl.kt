package com.example.quotesapp.domain.usecase.fetchquotes

import com.example.quotesapp.domain.model.QuoteDomain
import com.example.quotesapp.domain.repository.QuoteRepository
import com.example.quotesapp.domain.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FetchQuotesUseCaseImpl(
    private val quotesRepository: QuoteRepository,
    private val context: CoroutineDispatcher,
) : FetchQuotesUseCase {
    override suspend fun fetchQuotes(): Result<List<QuoteDomain.QuoteDomainModel>> {
        return withContext(context) {
           val data =  quotesRepository.fetchQuotes()
            data
        }
    }
}