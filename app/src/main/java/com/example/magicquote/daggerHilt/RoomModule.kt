package com.example.magicquote.daggerHilt

import android.content.Context
import androidx.room.Room
import com.example.magicquote.database.QuoteDao
import com.example.magicquote.database.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): QuoteDatabase =
        Room.databaseBuilder(
            context = context,
            QuoteDatabase::class.java,
            "q_db"
            ).build()
    @Singleton
    @Provides
    fun provideQuoteDao(quoteDatabase: QuoteDatabase): QuoteDao =
        quoteDatabase.getQuoteDao()

}