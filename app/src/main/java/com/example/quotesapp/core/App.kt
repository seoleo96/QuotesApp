package com.example.quotesapp.core

import android.app.Application
import com.example.quotesapp.data.datasource.local.QuoteCacheDataSourceImpl
import com.example.quotesapp.data.datasource.local.QuotesCacheToAuthorMapperImpl
import com.example.quotesapp.data.datasource.net.QuoteCloudDataSourceImpl
import com.example.quotesapp.data.quote.mapper.QuoteDataToCacheMapperImpl
import com.example.quotesapp.data.quote.mapper.QuoteDataToDomainMapperImpl
import com.example.quotesapp.data.quote.mapper.QuotesDataToDomainMapperImpl
import com.example.quotesapp.data.repository.AuthorRepositoryImpl
import com.example.quotesapp.data.repository.QuoteRepositoryImpl
import com.example.quotesapp.data.service.QuotesService
import com.example.quotesapp.data.service.RetrofitInstance
import com.example.quotesapp.domain.usecase.authors.FetchAuthorsUseCaseImpl
import com.example.quotesapp.domain.usecase.fetchquotes.FetchFavouritesCaseImpl
import com.example.quotesapp.domain.usecase.fetchquotes.FetchQuotesUseCaseImpl
import com.example.quotesapp.domain.usecase.fetchquotes.SaveAndDeleteFavouritesUseCaseImpl
import com.example.quotesapp.ui.authors.AuthorDomainToUIMapperImpl
import com.example.quotesapp.ui.authors.AuthorsCommunicationImpl
import com.example.quotesapp.ui.authors.AuthorsViewModel
import com.example.quotesapp.ui.favourites.FavDomainToUIMapperImpl
import com.example.quotesapp.ui.favourites.FavouritesCommunicationImpl
import com.example.quotesapp.ui.favourites.FavouritesViewModel
import com.example.quotesapp.ui.home.mapper.QuoteDomainTOUIMapperImpl
import com.example.quotesapp.ui.home.viewmodel.HomeViewModel
import com.example.quotesapp.ui.home.viewmodel.QuotesCommunicationImpl
import kotlinx.coroutines.*

class App : Application() {


    lateinit var homeViewModel: HomeViewModel
    lateinit var authorViewModel: AuthorsViewModel
    lateinit var favViewModel: FavouritesViewModel
    override fun onCreate() {
        super.onCreate()
        val service: QuotesService = RetrofitInstance().quoteService
        val quoteCloudDataSource = QuoteCloudDataSourceImpl(service)
        val quoteDataToDomainMapper = QuoteDataToDomainMapperImpl()
        val quotesDataToDomainMapper = QuotesDataToDomainMapperImpl(quoteDataToDomainMapper)
        val quoteCacheDataSource = QuoteCacheDataSourceImpl(this)
        val quotesCacheToAuthorData = QuotesCacheToAuthorMapperImpl()
        val quoteDataToCacheMapper = QuoteDataToCacheMapperImpl()
        val quotesRepository = QuoteRepositoryImpl(
            quoteCloudDataSource = quoteCloudDataSource,
            quotesDataToDomainMapper = quotesDataToDomainMapper,
            context = Dispatchers.IO,
            quoteCacheDataSource = quoteCacheDataSource,
            quoteDataToCacheMapper = quoteDataToCacheMapper
        )

        val quotesInteractor = FetchQuotesUseCaseImpl(quotesRepository, Dispatchers.Default)
        val quoteDomainToUIMapper = QuoteDomainTOUIMapperImpl()
        val quotesCommunication = QuotesCommunicationImpl()
        val saveAndDeleteFavouritesUseCase = SaveAndDeleteFavouritesUseCaseImpl(quotesRepository, Dispatchers.Default)
        homeViewModel = HomeViewModel(quotesInteractor, quoteDomainToUIMapper, quotesCommunication, saveAndDeleteFavouritesUseCase)
        val authorDomainToUIMapper = AuthorDomainToUIMapperImpl()
        val authorCommunication = AuthorsCommunicationImpl()
        val authorRepository = AuthorRepositoryImpl(
            Dispatchers.IO, quoteCacheDataSource, quotesCacheToAuthorData,
        )
        val authorUseCase = FetchAuthorsUseCaseImpl(authorRepository, Dispatchers.Default)
        authorViewModel =
            AuthorsViewModel(authorUseCase, authorDomainToUIMapper, authorCommunication)

        val fetchFavouritesUseCase = FetchFavouritesCaseImpl(quotesRepository)
        val favDomainToUIMapper = FavDomainToUIMapperImpl()
        val favCommunication = FavouritesCommunicationImpl()
        favViewModel = FavouritesViewModel(
            fetchFavouritesUseCase,
            favDomainToUIMapper,
            favCommunication
        )
    }
}