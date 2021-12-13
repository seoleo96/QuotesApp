package com.example.quotesapp.ui.authors


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class AuthorsCommunicationImpl : AuthorsCommunication {
    private val liveData = MutableLiveData<List<AuthorUIState>>()
    override fun map(authorUI: List<AuthorUIState>) {
        liveData.value = authorUI
    }

    override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<List<AuthorUIState>>) {
        liveData.observe(lifecycleOwner, observer)
    }
}