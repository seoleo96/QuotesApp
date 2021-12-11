package com.example.quotesapp.data.quote.model

import com.example.quotesapp.core.Mapper
import com.example.quotesapp.data.quote.mapper.QuoteDataToDomainMapper
import com.example.quotesapp.domain.model.QuoteDomain
import com.google.gson.annotations.SerializedName

data class QuoteDataModel(
    @SerializedName("text")
    private val text: String,
    @SerializedName("author")
    private val author: String?,
) : Mapper<QuoteDomain.QuoteDomainModel, QuoteDataToDomainMapper> {
    override fun map(mapper: QuoteDataToDomainMapper): QuoteDomain.QuoteDomainModel {
        return mapper.map(text, author ?: "Aleksandr Bogdanov")
    }
}
