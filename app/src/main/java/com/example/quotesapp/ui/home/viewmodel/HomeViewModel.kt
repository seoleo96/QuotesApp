package com.example.quotesapp.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.quotesapp.domain.model.QuoteDomain
import com.example.quotesapp.domain.usecase.fetchquotes.FetchQuotesUseCase
import com.example.quotesapp.domain.ResultData
import com.example.quotesapp.domain.usecase.fetchquotes.SaveAndDeleteFavouritesUseCase
import com.example.quotesapp.ui.home.mapper.QuoteDomainTOUIMapperImpl
import com.example.quotesapp.ui.QuoteUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchQuotesUseCase: FetchQuotesUseCase,
    private val quoteDomainToUIMapper: QuoteDomainTOUIMapperImpl,
    private val quotesCommunication: QuotesCommunication,
    private val saveAndDeleteFavouritesUseCase : SaveAndDeleteFavouritesUseCase
) : ViewModel() {
    fun fetchQuotes(query: String) {
        viewModelScope.launch {
            quotesCommunication.map(listOf(QuoteUIState.Progress))
            when (val quotesDomain = fetchQuotesUseCase.fetchQuotes(query)) {
                is ResultData.Success -> {
                    val quoteDomain: List<QuoteDomain.QuoteDomainModel> = quotesDomain.data
                    val quotesUI: List<QuoteUIState> = quoteDomain.map {
                        it.map(quoteDomainToUIMapper)
                    }
                    quotesCommunication.map(quotesUI)
                }

                is ResultData.Error -> {
                    quotesCommunication.map(listOf(QuoteUIState.Fail(quotesDomain.error)))
                }
            }
        }
    }

    fun observe(viewLifecycleOwner: LifecycleOwner, observer: Observer<List<QuoteUIState>>){
        quotesCommunication.observe(viewLifecycleOwner, observer)
    }

    fun updateQuote(isCheck: Boolean, author: String){
        viewModelScope.launch(Dispatchers.IO){
            fetchQuotesUseCase.updateQuote(isCheck, author)
        }
    }

    fun saveAndDeleteFavorites(toSave : Boolean, text : String) {
        viewModelScope.launch {
            saveAndDeleteFavouritesUseCase.toSaveAndDelete(toSave, text)
        }
    }
}

