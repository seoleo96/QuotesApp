package com.example.quotesapp.domain.usecase.fetchquotes

interface SaveAndDeleteFavouritesUseCase {
    suspend fun toSaveAndDelete(toSave : Boolean, text : String)
}