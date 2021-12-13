package com.example.quotesapp.domain.model

import com.example.quotesapp.domain.mapper.FavDomainToUIMapper
import com.example.quotesapp.domain.mapper.QuoteDomainToUIMapper

sealed class QuoteDomain {
    abstract fun <T> map(mapper: QuoteDomainToUIMapper<T>): T
    abstract fun <T> mapToFav(mapper: FavDomainToUIMapper<T>): T
    data class QuoteDomainModel(
        private val text: String,
        private val author: String,
        private val toSave: Boolean,
    ) : QuoteDomain() {
        override fun <T> map(mapper: QuoteDomainToUIMapper<T>): T {
            return mapper.map(text, author, toSave)
        }

        override fun <T> mapToFav(mapper: FavDomainToUIMapper<T>): T {
            return mapper.map(text, author, toSave)
        }
    }
}
