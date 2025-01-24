package com.example.magicquote.daggerHilt

import android.content.Context
import androidx.room.Room
import com.example.magicquote.dataBase.QuoteDao
import com.example.magicquote.dataBase.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context):QuoteDatabase =
        Room.databaseBuilder(context,
            QuoteDatabase::class.java,
            "q_db").build()

    @Provides
    @Singleton
    fun provideDao(quoteDatabase: QuoteDatabase):QuoteDao =
        quoteDatabase.getQuoteDao()
}