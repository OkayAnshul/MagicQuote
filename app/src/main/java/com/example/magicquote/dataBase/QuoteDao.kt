package com.example.magicquote.dataBase

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
    suspend fun addQuote(quote: Quote)

    @Update
    suspend fun updateQuote(quote: Quote)

    @Query("DELETE FROM quote_table_offline")
    suspend fun deleteAllQuotes()

    @Delete
    suspend fun removeQuote(quote: Quote)

    @Query("SELECT * FROM quote_table_offline")
    fun getAllQuotes(): Flow<List<Quote>>

    @Query("SELECT * FROM quote_table_offline WHERE id = :id")
    suspend fun getQuoteById(id: Long): Quote?

    @Query("SELECT * FROM quote_table_offline WHERE isFavourite = 1")
    fun getFavouriteQuotes(): Flow<List<Quote>>

    @Query("SELECT * FROM quote_table_offline WHERE category = :category")
    fun fetchQuotesByCategory(category: String): Flow<List<Quote>>

    @Query("SELECT * FROM quote_table_offline WHERE author = :author")
    fun fetchQuotesByAuthor(author: String): Flow<List<Quote>>

//    @Query("SELECT * FROM quote_table_offline WHERE text LIKE '%' || :query || '%'")
//    fun searchQuotes(query: String): Flow<List<Quote>>
}
