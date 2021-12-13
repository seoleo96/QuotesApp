package com.example.quotesapp.domain.usecase.authors

import com.example.quotesapp.domain.ResultData
import com.example.quotesapp.domain.model.AuthorDomain

interface FetchAuthorsUseCase {
    suspend fun fetchAuthors() : ResultData<List<AuthorDomain.AuthorDomainModel>>
    suspend fun fetchAuthorsByQuery(query : String) : ResultData<List<AuthorDomain.AuthorDomainModel>>
}