package com.example.quotesapp.ui.home.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.quotesapp.ui.QuoteUIState

interface QuotesCommunication {

    fun map(quotesUI: List<QuoteUIState>)
    fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<List<QuoteUIState>>)
}