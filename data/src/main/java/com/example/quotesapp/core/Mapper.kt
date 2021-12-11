package com.example.quotesapp.core

interface Mapper<T, M> {
    fun map(mapper: M): T
}