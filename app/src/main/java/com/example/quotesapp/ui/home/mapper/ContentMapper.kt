package com.example.quotesapp.ui.home.mapper


abstract class ContentMapper {
    abstract fun map(text: String, author: String = "1")
}