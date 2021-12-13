package com.example.quotesapp.data

import com.example.quotesapp.core.Mapper
import com.example.quotesapp.domain.model.AuthorDomain

data class AuthorData(
    private val author : String,
    private val isCheck : Boolean
) : Mapper<AuthorDomain, AuthorDataToDomainMapper>{
    override fun map(mapper: AuthorDataToDomainMapper): AuthorDomain.AuthorDomainModel {
        return mapper.map(author, isCheck)
    }
}