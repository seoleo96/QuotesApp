package com.example.quotesapp.domain.usecase.fetchquotes

import com.example.quotesapp.domain.model.QuoteDomain
import com.example.quotesapp.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow

class FetchFavouritesCaseImpl(
    private val quotesRepository: QuoteRepository,
) : FetchFavouritesCase {
    override fun fetchFavourites(): Flow<List<QuoteDomain.QuoteDomainModel>> {
        return quotesRepository.fetchFavourites()
    }
}