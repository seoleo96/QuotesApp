package com.example.quotesapp.data.quote.mapper

import com.example.quotesapp.data.quote.model.QuoteDataModel
import com.example.quotesapp.domain.model.QuoteDomain

class QuotesDataToDomainMapperImpl(
    private val quoteDataToDomainMapper: QuoteDataToDomainMapper
) : QuotesDataToDomainMapper {
    override fun map(quotesData: List<QuoteDataModel>): List<QuoteDomain.QuoteDomainModel> {
        return quotesData.map { quoteDataModel ->
            quoteDataModel.map(quoteDataToDomainMapper)
        }
    }
}