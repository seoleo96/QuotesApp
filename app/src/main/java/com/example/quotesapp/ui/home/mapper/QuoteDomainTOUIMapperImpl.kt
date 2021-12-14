package com.example.quotesapp.ui.home.mapper

import com.example.quotesapp.domain.mapper.QuoteDomainToUIMapper
import com.example.quotesapp.ui.QuoteUIState

class QuoteDomainTOUIMapperImpl : QuoteDomainToUIMapper<QuoteUIState> {
    override fun map(text: String, author: String, toSave : Boolean): QuoteUIState {
       return QuoteUIState.Success(text, author, toSave)
    }

    override fun map(e: Exception): QuoteUIState {
        return QuoteUIState.Fail(e.toString())
    }
}