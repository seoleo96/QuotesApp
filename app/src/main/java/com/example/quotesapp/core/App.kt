package com.example.quotesapp.core

import android.app.Application
import com.example.quotesapp.data.quote.mapper.QuoteDataToDomainMapperImpl
import com.example.quotesapp.data.repository.QuoteRepositoryImpl
import com.example.quotesapp.data.quote.mapper.QuotesDataToDomainMapperImpl
import com.example.quotesapp.data.datasource.net.QuoteCloudDataSourceImpl
import com.example.quotesapp.data.service.QuotesService
import com.example.quotesapp.data.service.RetrofitInstance
import com.example.quotesapp.domain.usecase.fetchquotes.FetchQuotesUseCaseImpl
import com.example.quotesapp.ui.home.mapper.QuoteDomainTOUIMapperImpl
import com.example.quotesapp.ui.home.viewmodel.HomeViewModel
import com.example.quotesapp.ui.home.viewmodel.QuotesCommunicationImpl
import kotlinx.coroutines.Dispatchers

class App : Application() {


    lateinit var homeViewModel: HomeViewModel
    override fun onCreate() {
        super.onCreate()
        val service: QuotesService = RetrofitInstance().quoteService
        val quoteCloudDataSource = QuoteCloudDataSourceImpl(service)
        val quoteDataToDomainMapper = QuoteDataToDomainMapperImpl()
        val quotesDataToDomainMapper = QuotesDataToDomainMapperImpl(quoteDataToDomainMapper)
        val quotesRepository = QuoteRepositoryImpl(quoteCloudDataSource = quoteCloudDataSource,
            quotesDataToDomainMapper = quotesDataToDomainMapper, context = Dispatchers.IO)

        val quotesInteractor = FetchQuotesUseCaseImpl(quotesRepository, Dispatchers.Default)
        val quoteDomainToUIMapper = QuoteDomainTOUIMapperImpl()
        val quotesCommunication = QuotesCommunicationImpl()
        homeViewModel = HomeViewModel(quotesInteractor, quoteDomainToUIMapper, quotesCommunication)
    }
}