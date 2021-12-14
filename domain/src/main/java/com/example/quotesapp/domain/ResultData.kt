package com.example.quotesapp.domain

sealed class ResultData<T> {
    data class Success<T>(val data: T) : ResultData<T>()
    data class Error<T>(val error: T) : ResultData<T>()
}
