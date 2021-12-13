package com.example.quotesapp.data.datasource.local

import com.example.quotesapp.data.AuthorData

class QuotesCacheToAuthorMapperImpl : QuotesCacheToAuthorMapper {
    override fun map(quotesCache: List<QuoteCacheModel>) = quotesCache.map { quoteCacheModel ->
        AuthorData(quoteCacheModel.author, quoteCacheModel.isCheck)
    }
}