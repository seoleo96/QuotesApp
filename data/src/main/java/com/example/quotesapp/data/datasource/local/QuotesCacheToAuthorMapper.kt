package com.example.quotesapp.data.datasource.local


import com.example.quotesapp.data.AuthorData

interface QuotesCacheToAuthorMapper {

    fun map(quotesCache: List<QuoteCacheModel>): List<AuthorData>
}