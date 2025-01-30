package com.example.magicquote.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuote(quote: QuoteTemplate)

    @Update
    suspend fun updateQuote(quote: QuoteTemplate)

    @Query("DELETE FROM quote_table_offline")
    suspend fun deleteAllQuotes()

    @Delete
    suspend fun removeQuote(quote: QuoteTemplate)

    @Query("SELECT * FROM quote_table_offline")
    fun getAllQuotes(): Flow<List<QuoteTemplate>>

    @Query("SELECT * FROM quote_table_offline WHERE id = :id")
    suspend fun getQuoteById(id: Long): QuoteTemplate?

    @Query("SELECT * FROM quote_table_offline WHERE isFavourite = 1")
    fun getFavouriteQuotes(): Flow<List<QuoteTemplate>>

    @Query("SELECT * FROM quote_table_offline WHERE category = :category")
    fun fetchQuotesByCategory(category: String): Flow<List<QuoteTemplate>>

    @Query("SELECT * FROM quote_table_offline WHERE author = :author")
    fun fetchQuotesByAuthor(author: String): Flow<List<QuoteTemplate>>

//    @Query("SELECT * FROM quote_table_offline WHERE text LIKE '%' || :query || '%'")
//    fun searchQuotes(query: String): Flow<List<Quote>>
}