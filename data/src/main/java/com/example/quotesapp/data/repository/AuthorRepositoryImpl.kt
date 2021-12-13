package com.example.quotesapp.data.repository

import com.example.quotesapp.data.datasource.local.QuoteCacheDataSource
import com.example.quotesapp.data.datasource.local.QuoteCacheModel
import com.example.quotesapp.data.datasource.local.QuotesCacheToAuthorMapperImpl
import com.example.quotesapp.domain.ResultData
import com.example.quotesapp.domain.model.AuthorDomain
import com.example.quotesapp.domain.repository.AuthorRepository
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class AuthorRepositoryImpl(
    private val context: CoroutineContext,
    private val quoteCacheDataSource: QuoteCacheDataSource,
    private val quotesCacheToAuthorData: QuotesCacheToAuthorMapperImpl,
) : AuthorRepository {

    override suspend fun fetchAuthors(): ResultData<List<AuthorDomain.AuthorDomainModel>> {
        return withContext(context) {
            val quotesCache: List<QuoteCacheModel> = quoteCacheDataSource.fetchAuthors()
            ResultData.Success(quotesCache.map {
                AuthorDomain.AuthorDomainModel(it.author, it.isCheck)
            })
        }
    }


    override suspend fun fetchAuthorsByQuery(query: String): ResultData<List<AuthorDomain.AuthorDomainModel>> {
        return withContext(context){
            val quoteCache: List<QuoteCacheModel> = quoteCacheDataSource.fetchAuthorsByQuery(query)
            ResultData.Success(quoteCache.map {
                AuthorDomain.AuthorDomainModel(it.author, it.isCheck)
            })
        }
    }

    suspend fun updateQuote(isCheck: Boolean, author: String) {
        quoteCacheDataSource.updateQuote(isCheck, author)
    }
}