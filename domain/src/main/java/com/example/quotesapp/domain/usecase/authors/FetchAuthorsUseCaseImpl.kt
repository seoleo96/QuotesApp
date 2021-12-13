package com.example.quotesapp.domain.usecase.authors

import com.example.quotesapp.domain.ResultData
import com.example.quotesapp.domain.model.AuthorDomain
import com.example.quotesapp.domain.repository.AuthorRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FetchAuthorsUseCaseImpl(
    private val authorRepository: AuthorRepository,
    private val context: CoroutineDispatcher,
) : FetchAuthorsUseCase {
    override suspend fun fetchAuthors(): ResultData<List<AuthorDomain.AuthorDomainModel>> {
        return withContext(context) {
            authorRepository.fetchAuthors()
        }
    }

    override suspend fun fetchAuthorsByQuery(query: String): ResultData<List<AuthorDomain.AuthorDomainModel>> {
        return withContext(context){
            authorRepository.fetchAuthorsByQuery(query)
        }
    }
}