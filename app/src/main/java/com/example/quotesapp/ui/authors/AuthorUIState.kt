package com.example.quotesapp.ui.authors

import com.example.quotesapp.core.Mapper

sealed class AuthorUIState : Mapper<Unit, AuthorContentMapper> {

    override fun map(mapper: AuthorContentMapper) = Unit

    object Progress : AuthorUIState()

    class Success(private val author: String, private val isCheck: Boolean) : AuthorUIState() {
        override fun map(mapper: AuthorContentMapper) {
            mapper.map(author, isCheck)
        }
    }
}