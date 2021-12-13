package com.example.quotesapp.data


import com.example.quotesapp.domain.model.AuthorDomain

interface AuthorDataToDomainMapper {
    fun map(author: String, isCheck: Boolean) : AuthorDomain.AuthorDomainModel
}