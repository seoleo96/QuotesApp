package com.example.quotesapp.data.quote.mapper

import com.example.quotesapp.domain.model.QuoteDomain

class QuoteDataToDomainMapperImpl : QuoteDataToDomainMapper {
    override fun map(text: String, author: String): QuoteDomain.QuoteDomainModel {
        return QuoteDomain.QuoteDomainModel(text, author)
    }
}