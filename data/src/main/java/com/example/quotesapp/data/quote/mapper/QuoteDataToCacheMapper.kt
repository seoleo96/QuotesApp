package com.example.quotesapp.data.quote.mapper

import com.example.quotesapp.data.datasource.local.QuoteCacheModel

interface QuoteDataToCacheMapper {
    fun map(text : String, author : String) : QuoteCacheModel
}