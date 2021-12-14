package com.example.quotesapp.data.di

import com.example.quotesapp.data.datasource.local.QuotesCacheToAuthorMapper
import com.example.quotesapp.data.datasource.local.QuotesCacheToAuthorMapperImpl
import com.example.quotesapp.data.quote.mapper.*
import com.example.quotesapp.data.repository.AuthorRepositoryImpl
import com.example.quotesapp.data.repository.QuoteRepositoryImpl
import com.example.quotesapp.domain.repository.AuthorRepository
import com.example.quotesapp.domain.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {

    fun quotesDataToDomainMapper(quoteDataToDomainMapper: QuoteDataToDomainMapper): QuotesDataToDomainMapper {
        return QuotesDataToDomainMapperImpl(quoteDataToDomainMapper)
    }

    fun quoteDataToDomainMapper(): QuoteDataToDomainMapper {
        return QuoteDataToDomainMapperImpl()
    }

    fun quotesCacheToAuthorMapper(): QuotesCacheToAuthorMapper {//QuotesCacheToAuthorMapper
        return QuotesCacheToAuthorMapperImpl()
    }

    fun quoteDataToCacheMapper(): QuoteDataToCacheMapper {
        return QuoteDataToCacheMapperImpl()
    }

    factory<QuotesDataToDomainMapper> {
        quotesDataToDomainMapper(quoteDataToDomainMapper = quoteDataToDomainMapper())
    }

    factory<QuoteDataToCacheMapper> {
        quoteDataToCacheMapper()
    }

    factory<QuotesCacheToAuthorMapper> {
        quotesCacheToAuthorMapper()
    }

    single<QuoteRepository> {
        QuoteRepositoryImpl(
            quoteCloudDataSource = get(),
            quotesDataToDomainMapper = get(),
            context = Dispatchers.IO,
            quoteCacheDataSource = get(),
            quoteDataToCacheMapper = get()
        )
    }

    single<AuthorRepository> {
        AuthorRepositoryImpl(
            context = Dispatchers.IO,
            quoteCacheDataSource = get()
        )
    }
}