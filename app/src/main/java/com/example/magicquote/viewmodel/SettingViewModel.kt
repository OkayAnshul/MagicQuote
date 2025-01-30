package com.example.magicquote.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magicquote.datastore.SettingRepository
import com.example.magicquote.datastore.TextFieldsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.textFieldsState.first() // Force early load
        }
    }
    private val _settingExpanded = mutableStateOf(false)
    val settingExpanded: State<Boolean> =_settingExpanded
    fun toggleSettingExpand(state:Boolean?=null){

        if(state != null)
            _settingExpanded.value=state
        else
        _settingExpanded.value=!_settingExpanded.value
    }

    val uiState = repository.textFieldsState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        TextFieldsState()
    )

    fun updateState(key: Preferences.Key<Boolean>, enabled: Boolean) {
        viewModelScope.launch {
            repository.updateTextFieldState(key, enabled)
        }
    }
}