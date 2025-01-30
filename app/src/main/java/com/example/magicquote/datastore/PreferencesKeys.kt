package com.example.magicquote.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferencesKeys {
    val contextState = booleanPreferencesKey("context_State")
    val authorState = booleanPreferencesKey("author_State")
    val categoryState = booleanPreferencesKey("category_State")
    val isFavSTate = booleanPreferencesKey("isFav_State")
}