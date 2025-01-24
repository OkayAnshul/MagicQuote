package com.example.magicquote.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity("quote_table_offline")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo("quote")
    val text: String,
    @ColumnInfo("context")
    val context:String?=null,
    @ColumnInfo("author")
    val author: String?=null,
    @ColumnInfo("category")
    val category: String?=null,
    @ColumnInfo("isFavourite")
    val isFavorite: Boolean=false,
    @ColumnInfo("createdAt")
    val createdAt: Long = System.currentTimeMillis(), // Timestamp for when the quote was created
    @ColumnInfo("updatedAt")
    val updatedAt: Long? = null                      // Timestamp for when the quote was last updated
)
