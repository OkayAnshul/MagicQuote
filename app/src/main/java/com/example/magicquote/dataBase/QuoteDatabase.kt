package com.example.magicquote.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Quote::class],
    version = 1,
    exportSchema = false)
abstract class QuoteDatabase: RoomDatabase() {
    /*In database, all type of Dao interface has been initialised
    from the skeleton(interface) we created for Dao */
    abstract fun getQuoteDao():QuoteDao
}
