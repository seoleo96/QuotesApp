package com.example.quotesapp.data.datasource.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quotes WHERE author LIKE '%'|| :query ||'%' ORDER BY id ASC")
    fun fetchQuotes(query: String): Flow<List<QuoteCacheModel>>

    @Query("SELECT * FROM quotes")
    fun fetchQuotesCheck(): List<QuoteCacheModel>

    @Query("SELECT * FROM quotes WHERE author LIKE '%'|| :query ||'%' ORDER BY author ASC")
    suspend fun fetchAuthorsByQuery(query: String): List<QuoteCacheModel>

    @Query("SELECT * FROM quotes ORDER BY author ASC")
    suspend fun fetchAuthors(): List<QuoteCacheModel>

    @Query("DELETE FROM quotes")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<QuoteCacheModel>)

    @Query("UPDATE quotes SET isCheck=:bool WHERE author = :author")
    fun updateQuote(bool: Boolean, author: String): Int

    @Query("UPDATE quotes SET isCheck=:bool WHERE isCheck = :tr")
    fun updateAll(bool: Boolean, tr : Boolean): Int

    @Query("UPDATE quotes SET toSave=:toSave WHERE text = :text")
    fun toSaveAndDelete(toSave: Boolean, text: String): Int

    @Query("SELECT * FROM quotes where toSave=1 ORDER BY id ASC")
    fun fetchFavourites(): Flow<List<QuoteCacheModel>>
}