package com.example.quotesapp.di


import com.example.quotesapp.domain.mapper.AuthorDomainToUIMapper
import com.example.quotesapp.domain.mapper.FavDomainToUIMapper
import com.example.quotesapp.domain.mapper.QuoteDomainToUIMapper
import com.example.quotesapp.ui.QuoteUIState
import com.example.quotesapp.ui.authors.*
import com.example.quotesapp.ui.favourites.*
import com.example.quotesapp.ui.home.mapper.QuoteDomainTOUIMapperImpl
import com.example.quotesapp.ui.home.viewmodel.HomeViewModel
import com.example.quotesapp.ui.home.viewmodel.QuotesCommunication
import com.example.quotesapp.ui.home.viewmodel.QuotesCommunicationImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    fun quoteDomainToUIMapper(): QuoteDomainToUIMapper<QuoteUIState> {
        return QuoteDomainTOUIMapperImpl()
    }

    fun favDomainToUIMapper(): FavDomainToUIMapper<FavouritesUIState> {
        return FavDomainToUIMapperImpl()
    }

    fun authorDomainToUIMapper(): AuthorDomainToUIMapper<AuthorUIState> {
        return AuthorDomainToUIMapperImpl()
    }

    fun quoteCommunication(): QuotesCommunication {
        return QuotesCommunicationImpl()
    }

    fun favCommunication(): FavouritesCommunication {
        return FavouritesCommunicationImpl()
    }

    fun authorCommunication(): AuthorsCommunication {
        return AuthorsCommunicationImpl()
    }

    factory<QuoteDomainToUIMapper<QuoteUIState>> { quoteDomainToUIMapper() }
    factory<QuotesCommunication> { quoteCommunication() }
    factory<FavouritesCommunication> { favCommunication() }
    factory<FavDomainToUIMapper<FavouritesUIState>> { favDomainToUIMapper() }
    factory<AuthorDomainToUIMapper<AuthorUIState>> { authorDomainToUIMapper() }
    factory<AuthorsCommunication> { authorCommunication() }

    viewModel<HomeViewModel> {
        HomeViewModel(fetchQuotesUseCase = get(),
            quoteDomainToUIMapper = get(),
            quotesCommunication = get(),
            saveAndDeleteFavouritesUseCase = get()
        )
    }

    viewModel<FavouritesViewModel> {
        FavouritesViewModel(
            fetchFavouritesCase = get(),
            favDomainToUIMapper = get(),
            favCommunication = get(), saveAndDeleteFavouritesUseCase = get()
        )
    }

    viewModel<AuthorsViewModel> {
        AuthorsViewModel(
            fetchAuthorsUseCase = get(),
            authorDomainToUIMapper = get(),
            authorCommunication = get()
        )
    }
}