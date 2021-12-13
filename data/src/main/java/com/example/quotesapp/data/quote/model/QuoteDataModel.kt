package com.example.quotesapp.data.quote.model

import com.example.quotesapp.core.Mapper
import com.example.quotesapp.core.ToCacheMapper
import com.example.quotesapp.data.datasource.local.QuoteCacheModel
import com.example.quotesapp.data.quote.mapper.QuoteDataToCacheMapper
import com.example.quotesapp.data.quote.mapper.QuoteDataToDomainMapper
import com.example.quotesapp.domain.model.QuoteDomain
import com.google.gson.annotations.SerializedName

data class QuoteDataModel(
    @SerializedName("text")
    private val text: String,
    @SerializedName("author")
    private val author: String?,
) : Mapper<QuoteDomain.QuoteDomainModel, QuoteDataToDomainMapper>,
    ToCacheMapper<QuoteCacheModel, QuoteDataToCacheMapper> {
    override fun map(mapper: QuoteDataToDomainMapper): QuoteDomain.QuoteDomainModel {
        return mapper.map(text, author ?: "Aleksandr Bogdanov", false)
    }

    override fun toCacheMap(mapper: QuoteDataToCacheMapper): QuoteCacheModel {
        return mapper.map(text, author ?: "Aleksandr Bogdanov")
    }
}
