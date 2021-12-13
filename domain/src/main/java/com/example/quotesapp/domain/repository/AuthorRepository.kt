package com.example.quotesapp.domain.repository

import com.example.quotesapp.domain.ResultData
import com.example.quotesapp.domain.model.AuthorDomain

interface AuthorRepository {
    suspend fun fetchAuthors() : ResultData<List<AuthorDomain.AuthorDomainModel>>
    suspend fun fetchAuthorsByQuery(query : String) : ResultData<List<AuthorDomain.AuthorDomainModel>>
}