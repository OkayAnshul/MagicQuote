package com.example.magicquote.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>   // Inject DataStore
) {
    // Use the injected DataStore directly
    val textFieldsState: Flow<TextFieldsState> = dataStore.data
        .map { preferences ->
            TextFieldsState(
                contextState = preferences[PreferencesKeys.contextState] ?: false,
                authorState = preferences[PreferencesKeys.authorState] ?: false,
                categoryState = preferences[PreferencesKeys.categoryState] ?:true,
                isFavState = preferences[PreferencesKeys.isFavSTate]?:true
            )
        }

    suspend fun updateTextFieldState(key: Preferences.Key<Boolean>, enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[key] = enabled
        }
    }
}
// Data class to hold all TextField states
data class TextFieldsState(
    val contextState: Boolean = true,
    val authorState: Boolean = true,
    val categoryState: Boolean = true,
    val isFavState: Boolean = true
)
