package com.example.quotesapp.data.datasource.di


import com.example.quotesapp.data.datasource.local.QuoteCacheDataSource
import com.example.quotesapp.data.datasource.local.QuoteCacheDataSourceImpl
import com.example.quotesapp.data.datasource.local.QuoteDB
import com.example.quotesapp.data.datasource.local.QuoteDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {

    fun getDao(database: QuoteDB): QuoteDao {
        return database.quoteDao()
    }
    single<QuoteDB> { QuoteDB.getDatabase(androidContext()) }
    single<QuoteDao> { getDao(database = get()) }
    single<QuoteCacheDataSource> { QuoteCacheDataSourceImpl( context = androidContext()) }
}



