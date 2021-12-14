package com.example.quotesapp.ui.favourites

import com.example.quotesapp.core.Mapper

sealed class FavouritesUIState : Mapper<Unit, FavouritesContentMapper> {

    object Progress : FavouritesUIState()

    class Success(
        private val text: String,
        private val author: String,
        private val toSave: Boolean,
    ) : FavouritesUIState() {
        override fun map(mapper: FavouritesContentMapper) {
            mapper.map(text, author, toSave)
        }
    }

    class Empty(
        private val text: String,
    ) : FavouritesUIState() {
        override fun map(mapper: FavouritesContentMapper) {
            mapper.map(text)
        }
    }

    override fun map(mapper: FavouritesContentMapper) = Unit
}
