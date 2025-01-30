package com.example.magicquote.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.magicquote.layout.NewQuoteScreen
import com.example.magicquote.layout.SavedQuoteScreen
import com.example.magicquote.layout.SettingScreen
import com.example.magicquote.viewmodel.QuoteViewModel
import com.example.magicquote.viewmodel.SettingsViewModel

@Composable
fun QuoteNav(navHostController: NavHostController,
             paddingValues: PaddingValues,
             viewModel: QuoteViewModel,
             settingsViewModel: SettingsViewModel
){


    NavHost(navController = navHostController,
        startDestination = ScreenNav.HomeScreen.route,
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues))
    {
        composable(ScreenNav.HomeScreen.route){
            SavedQuoteScreen(viewModel = viewModel,
                navHostController = navHostController)
        }
        composable(ScreenNav.NewQuoteScreen.route){
            NewQuoteScreen(navHostController = navHostController,
                viewmodel = viewModel,
                settingsViewModel =settingsViewModel )
        }
        composable(ScreenNav.SettingScreen.route){
            SettingScreen()
        }
    }
 }