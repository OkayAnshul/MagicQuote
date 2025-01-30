package com.example.magicquote.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.magicquote.components.OptionalSwitchField

import com.example.magicquote.viewmodel.SettingsViewModel

@Composable
fun SettingScreen(settingsViewModel: SettingsViewModel = hiltViewModel()){

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        OptionalSwitchField(viewModel = settingsViewModel)
    }


}