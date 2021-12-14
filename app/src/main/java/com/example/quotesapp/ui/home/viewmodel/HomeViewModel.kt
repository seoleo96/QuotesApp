package com.example.quotesapp.ui.home.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.domain.mapper.QuoteDomainToUIMapper
import com.example.quotesapp.domain.model.QuoteDomain
import com.example.quotesapp.domain.usecase.fetchquotes.FetchQuotesUseCase
import com.example.quotesapp.domain.usecase.fetchquotes.SaveAndDeleteFavouritesUseCase
import com.example.quotesapp.ui.QuoteUIState
import com.example.quotesapp.ui.home.mapper.QuoteDomainTOUIMapperImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchQuotesUseCase: FetchQuotesUseCase,
    private val quoteDomainToUIMapper: QuoteDomainToUIMapper<QuoteUIState>,
    private val quotesCommunication: QuotesCommunication,
    private val saveAndDeleteFavouritesUseCase: SaveAndDeleteFavouritesUseCase,
) : ViewModel() {

    fun fetchQuotes(query: String, requireContext: Context) {
        viewModelScope.launch {
            quotesCommunication.map(listOf(QuoteUIState.Progress))
            fetchQuotesUseCase.fetchQuotesCache(query).collect { list ->
                if (list.isEmpty()) {
                    if (checkInternetConnection(requireContext)) {
                        val quotes: Flow<List<QuoteDomain.QuoteDomainModel>> =
                            fetchQuotesUseCase.fetchQuotesCloud()
                        quotes
                            .catch {
                                quotesCommunication.map(listOf(QuoteUIState.Fail(it.message.toString())))
                            }
                            .collect { listQ ->
                                val quotes: List<QuoteUIState> = listQ.map {
                                    it.map(quoteDomainToUIMapper)
                                }
                                quotesCommunication.map(quotes)
                            }
                    } else {
                        quotesCommunication.map(listOf(QuoteUIState.Fail("No Internet connection")))
                    }
                } else {
                    val quotesDomain: Flow<List<QuoteDomain.QuoteDomainModel>> =
                        fetchQuotesUseCase.fetchQuotesCache(query)
                    quotesDomain
                        .collect { list ->
                            val quotes: List<QuoteUIState> = list.map {
                                it.map(quoteDomainToUIMapper)
                            }
                            quotesCommunication.map(quotes)
                        }
                }
            }
        }
    }


    fun observe(viewLifecycleOwner: LifecycleOwner, observer: Observer<List<QuoteUIState>>) {
        quotesCommunication.observe(viewLifecycleOwner, observer)
    }

    fun updateQuote(isCheck: Boolean, author: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchQuotesUseCase.updateQuote(isCheck, author)
        }
    }

    fun saveAndDeleteFavorites(toSave: Boolean, text: String) {
        viewModelScope.launch {
            saveAndDeleteFavouritesUseCase.toSaveAndDelete(toSave, text)
        }
    }

    private fun checkInternetConnection(context: Context): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

}

