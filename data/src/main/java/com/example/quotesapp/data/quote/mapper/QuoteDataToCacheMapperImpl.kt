package com.example.quotesapp.data.quote.mapper

import com.example.quotesapp.data.datasource.local.QuoteCacheModel

class QuoteDataToCacheMapperImpl : QuoteDataToCacheMapper {
    override fun map(text: String, author: String): QuoteCacheModel {
        return QuoteCacheModel(0, text, author, false)
    }
}