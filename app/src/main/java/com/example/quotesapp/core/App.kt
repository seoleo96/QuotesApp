package com.example.quotesapp.core

import android.app.Application
import com.example.quotesapp.data.datasource.di.localModule
import com.example.quotesapp.data.di.repositoryModule
import com.example.quotesapp.data.service.di.cloudModule
import com.example.quotesapp.di.domainModule
import com.example.quotesapp.di.viewModelModule
import kotlinx.coroutines.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {


    //    lateinit var homeViewModel: HomeViewModel
//    lateinit var authorViewModel: AuthorsViewModel
//    lateinit var favViewModel: FavouritesViewModel
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(applicationContext)
            modules(
                viewModelModule,
                domainModule,
                cloudModule,
                repositoryModule,
                localModule
            )
        }
////        val service: QuotesService = RetrofitInstance().quoteService
////        val quoteCloudDataSource = QuoteCloudDataSourceImpl(service)
//        val quoteDataToDomainMapper = QuoteDataToDomainMapperImpl()
//        val quotesDataToDomainMapper = QuotesDataToDomainMapperImpl(quoteDataToDomainMapper)
//        val quoteCacheDataSource = QuoteCacheDataSourceImpl(this)
//        val quotesCacheToAuthorData = QuotesCacheToAuthorMapperImpl()
//        val quoteDataToCacheMapper = QuoteDataToCacheMapperImpl()
//        val quotesRepository = QuoteRepositoryImpl(
//            quoteCloudDataSource = quoteCloudDataSource,
//            quotesDataToDomainMapper = quotesDataToDomainMapper,
//            context = Dispatchers.IO,
//            quoteCacheDataSource = quoteCacheDataSource,
//            quoteDataToCacheMapper = quoteDataToCacheMapper
//        )
//
//        val quotesInteractor = FetchQuotesUseCaseImpl(quotesRepository, Dispatchers.Default)
//        val quoteDomainToUIMapper = QuoteDomainTOUIMapperImpl()
//        val quotesCommunication = QuotesCommunicationImpl()
//        val saveAndDeleteFavouritesUseCase = SaveAndDeleteFavouritesUseCaseImpl(quotesRepository, Dispatchers.Default)
//        homeViewModel = HomeViewModel(quotesInteractor, quoteDomainToUIMapper, quotesCommunication, saveAndDeleteFavouritesUseCase)
//
//
//        val authorRepository = AuthorRepositoryImpl(
//            Dispatchers.IO, quoteCacheDataSource, quotesCacheToAuthorData,
//        )
//        val authorDomainToUIMapper = AuthorDomainToUIMapperImpl()
//        val authorCommunication = AuthorsCommunicationImpl()
//        val authorUseCase = FetchAuthorsUseCaseImpl(authorRepository, Dispatchers.Default)
//        authorViewModel =
//            AuthorsViewModel(authorUseCase, authorDomainToUIMapper, authorCommunication)
//
//        val fetchFavouritesUseCase = FetchFavouritesCaseImpl(quotesRepository)
//        val favDomainToUIMapper = FavDomainToUIMapperImpl()
//        val favCommunication = FavouritesCommunicationImpl()
//        favViewModel = FavouritesViewModel(
//            fetchFavouritesUseCase,
//            favDomainToUIMapper,
//            favCommunication,
//            saveAndDeleteFavouritesUseCase
//        )
    }
}