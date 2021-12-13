package com.example.quotesapp.ui.home.mapper


interface ContentMapper {
    fun map(text: String, author: String = "1", toSave: Boolean = false)
}