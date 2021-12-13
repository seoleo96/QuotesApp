package com.example.quotesapp.ui.favourites

import com.example.quotesapp.domain.mapper.FavDomainToUIMapper

class FavDomainToUIMapperImpl : FavDomainToUIMapper<FavouritesUIState> {
    override fun map(text: String, author: String, toSave: Boolean): FavouritesUIState {
        return FavouritesUIState.Success(text, author, toSave)
    }
}