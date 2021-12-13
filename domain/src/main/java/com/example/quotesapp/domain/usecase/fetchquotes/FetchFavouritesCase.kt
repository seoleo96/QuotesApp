package com.example.quotesapp.domain.usecase.fetchquotes

import com.example.quotesapp.domain.model.QuoteDomain
import kotlinx.coroutines.flow.Flow

interface FetchFavouritesCase {
    fun fetchFavourites(): Flow<List<QuoteDomain.QuoteDomainModel>>
}