package com.example.quotesapp.domain.model

import com.example.quotesapp.domain.mapper.QuoteDomainToUIMapper

sealed class QuoteDomain {
    abstract fun <T> map(mapper: QuoteDomainToUIMapper<T>): T
    data class QuoteDomainModel(
        private val text: String,
        private val author: String,
    ) : QuoteDomain() {
        override fun <T> map(mapper: QuoteDomainToUIMapper<T>): T {
            return mapper.map(text, author)
        }
    }
}
