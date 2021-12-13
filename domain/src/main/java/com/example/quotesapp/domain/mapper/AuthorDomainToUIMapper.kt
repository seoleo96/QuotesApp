package com.example.quotesapp.domain.mapper

interface AuthorDomainToUIMapper<T> {
    fun map(text: String, isCheck: Boolean): T
}