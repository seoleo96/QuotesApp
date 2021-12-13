package com.example.quotesapp.ui.favourites

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.quotesapp.ui.QuoteUIState

interface FavouritesCommunication {

    fun map(favUiState: List<FavouritesUIState>)
    fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<List<FavouritesUIState>>)
}