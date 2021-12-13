package com.example.quotesapp.data

import com.example.quotesapp.domain.model.AuthorDomain


class AuthorDataToDomainMapperImpl : AuthorDataToDomainMapper {
    override fun map(author: String, isCheck: Boolean) = AuthorDomain.AuthorDomainModel(author, isCheck)
}