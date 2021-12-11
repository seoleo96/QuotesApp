package com.example.quotesapp.ui.home.viewmodel


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.quotesapp.ui.QuoteUIState

class QuotesCommunicationImpl : QuotesCommunication {
    private val quotesLiveData = MutableLiveData<List<QuoteUIState>>()
    override fun map(quotesUI: List<QuoteUIState>) {
        quotesLiveData.value = quotesUI
    }

    override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<List<QuoteUIState>>) {
        quotesLiveData.observe(lifecycleOwner, observer)
    }
}