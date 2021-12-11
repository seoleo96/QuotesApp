package com.example.quotesapp.domain.mapper

interface QuoteDomainToUIMapper<T> {
    fun map(text: String, author: String): T
    fun map(e: Exception): T
}