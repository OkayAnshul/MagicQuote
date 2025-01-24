package com.example.magicquote.dataBase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val dao: QuoteDao) {

    suspend fun addQuote(quote: Quote){
        dao.addQuote(quote)
    }
    suspend fun removeQuote(quote: Quote){
        dao.removeQuote(quote)
    }
    suspend fun removeAll(){
        dao.deleteAllQuotes()
    }
    suspend fun getQuoteById(id:Long){
        dao.getQuoteById(id)
    }
    suspend fun updateQuote(quote: Quote){
        dao.updateQuote(quote)
    }
    fun getAllQuote():Flow<List<Quote>> =
        dao.getAllQuotes().flowOn(Dispatchers.IO)
            .conflate()
}