package com.example.quotesapp.ui

import com.example.quotesapp.core.Mapper
import com.example.quotesapp.ui.home.mapper.ContentMapper

sealed class QuoteUIState : Mapper<Unit, ContentMapper> {

    object Progress : QuoteUIState()

    class Success(
        private val text: String,
        private val author: String,
        private val toSave: Boolean,
    ) : QuoteUIState() {
        override fun map(mapper: ContentMapper) {
            mapper.map(text, author, toSave)
        }
    }

    class Fail(private val errorType: Throwable) : QuoteUIState() {
        override fun map(mapper: ContentMapper) {
            mapper.map(errorType.message.toString())
        }
    }

    override fun map(mapper: ContentMapper) = Unit
}
