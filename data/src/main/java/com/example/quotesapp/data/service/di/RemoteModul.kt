package com.example.quotesapp.data.service.di

import com.example.quotesapp.data.datasource.net.QuoteCloudDataSource
import com.example.quotesapp.data.datasource.net.QuoteCloudDataSourceImpl
import com.example.quotesapp.data.service.QuotesService
import com.example.quotesapp.data.service.RetrofitInstance
import org.koin.dsl.module
import retrofit2.Retrofit

val cloudModule = module {
    single<Retrofit> { RetrofitInstance().instance() }
    factory<QuotesService> { RetrofitInstance().retrofitService(retrofit = get()) }
    factory<QuoteCloudDataSource> { QuoteCloudDataSourceImpl(service = get()) }
}

