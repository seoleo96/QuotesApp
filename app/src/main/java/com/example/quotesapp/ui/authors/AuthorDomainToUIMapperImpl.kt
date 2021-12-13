package com.example.quotesapp.ui.authors

import com.example.quotesapp.domain.mapper.AuthorDomainToUIMapper

class AuthorDomainToUIMapperImpl : AuthorDomainToUIMapper<AuthorUIState> {
    override fun map(author: String, isCheck: Boolean): AuthorUIState {
        return AuthorUIState.Success(author, isCheck)
    }
}