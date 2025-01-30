package com.example.magicquote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.magicquote.layout.MainLayout
import com.example.magicquote.theme.MagicQuoteTheme
import com.example.magicquote.viewmodel.QuoteViewModel
import com.example.magicquote.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            val navHostController: NavHostController = rememberNavController()
            val viewModel: QuoteViewModel = hiltViewModel()
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            MagicQuoteTheme {
                MainLayout(navHostController = navHostController,
                    viewModel = viewModel,
                    settingsViewModel = settingsViewModel)
            }
        }
    }
}

