package com.example.quotesapp.domain.usecase.fetchquotes

import com.example.quotesapp.domain.repository.QuoteRepository
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class SaveAndDeleteFavouritesUseCaseImpl(
    private val quotesRepository: QuoteRepository,
    private val context: CoroutineContext,
    ) :  SaveAndDeleteFavouritesUseCase{
    override suspend fun toSaveAndDelete(toSave: Boolean, text: String) {
        withContext(context){
            quotesRepository.toSaveAndDelete(toSave, text)
        }
    }
}