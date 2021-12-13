package com.example.quotesapp.ui.authors


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface AuthorsCommunication {

    fun map(quotesUI: List<AuthorUIState>)
    fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<List<AuthorUIState>>)
}