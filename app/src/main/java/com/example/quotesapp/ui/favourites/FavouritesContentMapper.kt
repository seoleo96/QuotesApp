package com.example.quotesapp.ui.favourites


interface FavouritesContentMapper {
    fun map(text: String, author: String = "1", toSave : Boolean = false)
}