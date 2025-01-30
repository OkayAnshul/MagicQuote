package com.example.magicquote.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities =
[QuoteTemplate::class],
    version = 1,
    exportSchema = false)

abstract class QuoteDatabase :RoomDatabase() {
    abstract fun getQuoteDao(): QuoteDao
}