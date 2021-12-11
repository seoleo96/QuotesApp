package com.example.quotesapp.ui.home.viewmodel

import androidx.lifecycle.*
import com.example.quotesapp.domain.model.QuoteDomain
import com.example.quotesapp.domain.usecase.fetchquotes.FetchQuotesUseCase
import com.example.quotesapp.domain.Result
import com.example.quotesapp.ui.home.mapper.QuoteDomainTOUIMapperImpl
import com.example.quotesapp.ui.QuoteUIState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchQuotesUseCase: FetchQuotesUseCase,
    private val quoteDomainToUIMapper: QuoteDomainTOUIMapperImpl,
    private val quotesCommunication: QuotesCommunication,
) : ViewModel() {
    fun fetchQuotes() {
        viewModelScope.launch {
            quotesCommunication.map(listOf(QuoteUIState.Progress))
            when (val quotesDomain = fetchQuotesUseCase.fetchQuotes()) {
                is Result.Success -> {
                    val quoteDomain: List<QuoteDomain.QuoteDomainModel> = quotesDomain.data
                    val quotesUI: List<QuoteUIState> = quoteDomain.map {
                        it.map(quoteDomainToUIMapper)
                    }
                    quotesCommunication.map(quotesUI)
                }

                is Result.Error -> {
                    quotesCommunication.map(listOf(QuoteUIState.Fail(quotesDomain.error)))
                }
            }
        }
    }

    fun observe(viewLifecycleOwner: LifecycleOwner, observer: Observer<List<QuoteUIState>>){
        quotesCommunication.observe(viewLifecycleOwner, observer)
    }
}

