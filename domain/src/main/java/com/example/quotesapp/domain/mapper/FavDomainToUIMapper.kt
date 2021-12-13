package com.example.quotesapp.domain.mapper

interface FavDomainToUIMapper<T> {
    fun map(text: String, author: String, toSave: Boolean): T
}