package com.example.quotesapp.data.datasource.local


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteCacheModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    val author: String,
    val isCheck: Boolean = false,
    val toSave: Boolean = false
)


