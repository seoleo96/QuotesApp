package com.example.quotesapp.data.quote.mapper

import com.example.quotesapp.data.quote.model.QuoteDataModel
import com.example.quotesapp.domain.model.QuoteDomain

interface QuotesDataToDomainMapper {
    fun map(quotesData: List<QuoteDataModel>): List<QuoteDomain.QuoteDomainModel>
}