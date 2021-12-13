package com.example.quotesapp.ui.favourites


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class FavouritesCommunicationImpl : FavouritesCommunication {
    private val favouritesLiveData = MutableLiveData<List<FavouritesUIState>>()
    override fun map(favUiState: List<FavouritesUIState>) {
        favouritesLiveData.value = favUiState
    }

    override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<List<FavouritesUIState>>) {
        favouritesLiveData.observe(lifecycleOwner, observer)
    }
}