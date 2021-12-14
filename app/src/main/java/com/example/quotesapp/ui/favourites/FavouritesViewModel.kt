package com.example.quotesapp.ui.favourites

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.domain.mapper.FavDomainToUIMapper
import com.example.quotesapp.domain.model.QuoteDomain
import com.example.quotesapp.domain.usecase.fetchquotes.FetchFavouritesCase
import com.example.quotesapp.domain.usecase.fetchquotes.SaveAndDeleteFavouritesUseCase
import com.example.quotesapp.ui.QuoteUIState
import com.example.quotesapp.ui.home.mapper.QuoteDomainTOUIMapperImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val fetchFavouritesCase: FetchFavouritesCase,
    private val favDomainToUIMapper: FavDomainToUIMapper<FavouritesUIState>,
    private val favCommunication: FavouritesCommunication,
    private val saveAndDeleteFavouritesUseCase: SaveAndDeleteFavouritesUseCase,
    ) : ViewModel() {
    fun fetchFavourites() {
        viewModelScope.launch {
            favCommunication.map(listOf(FavouritesUIState.Progress))
            val favDomains: Flow<List<QuoteDomain.QuoteDomainModel>> = fetchFavouritesCase.fetchFavourites()
            val data: Flow<List<FavouritesUIState>> = favDomains.map { list ->
                list.map { favDomain->
                    favDomain.mapToFav(favDomainToUIMapper)
                }
            }
            data.collect {
                if (it.isEmpty()){
                    favCommunication.map(listOf(FavouritesUIState.Empty("NO DATA")))
                }else{
                    favCommunication.map(it)
                }
            }

        }
    }

    fun saveAndDeleteFavorites(toSave: Boolean, text: String) {
        viewModelScope.launch {
            saveAndDeleteFavouritesUseCase.toSaveAndDelete(toSave, text)
        }
    }
    fun observe(viewLifecycleOwner: LifecycleOwner, observer: Observer<List<FavouritesUIState>>) {
        favCommunication.observe(viewLifecycleOwner, observer)
    }
}

