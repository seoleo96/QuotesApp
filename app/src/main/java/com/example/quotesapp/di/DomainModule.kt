package com.example.quotesapp.di

import com.example.quotesapp.domain.usecase.authors.FetchAuthorsUseCase
import com.example.quotesapp.domain.usecase.authors.FetchAuthorsUseCaseImpl
import com.example.quotesapp.domain.usecase.fetchquotes.*
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val domainModule = module {

    factory<FetchQuotesUseCase> {
        FetchQuotesUseCaseImpl(quotesRepository = get(), Dispatchers.Default)
    }

    factory<FetchFavouritesCase> {
        FetchFavouritesCaseImpl(quotesRepository = get())
    }

    factory<FetchAuthorsUseCase> {
        FetchAuthorsUseCaseImpl(authorRepository = get(), Dispatchers.Default)
    }

    factory<SaveAndDeleteFavouritesUseCase> {
        SaveAndDeleteFavouritesUseCaseImpl(quotesRepository = get(), Dispatchers.Default)
    }
}