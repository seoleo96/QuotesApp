package com.example.quotesapp.data.quote.mapper


import com.example.quotesapp.domain.model.QuoteDomain

interface QuoteDataToDomainMapper {
    fun map(text : String, author : String) : QuoteDomain.QuoteDomainModel
}