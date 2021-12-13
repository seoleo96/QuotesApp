package com.example.quotesapp.domain.model

import com.example.quotesapp.domain.mapper.AuthorDomainToUIMapper

sealed class AuthorDomain {
    abstract fun <T> map(mapper: AuthorDomainToUIMapper<T>): T
    data class AuthorDomainModel(
        private val text: String,
        private val isCheck: Boolean,
    ) : AuthorDomain() {
        override fun <T> map(mapper: AuthorDomainToUIMapper<T>): T {
            return mapper.map(text, isCheck)
        }
    }
}
