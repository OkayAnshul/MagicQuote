package com.example.magicquote.quoteViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.magicquote.navigation.ScreenNav
import kotlinx.coroutines.launch


class MagicViewModel:ViewModel() {

    private val _isInputDrawerExpanded = mutableStateOf(true)
    val isInputDrawerExpanded: State<Boolean> get() = _isInputDrawerExpanded

    // Toggles the expand state
     fun toggleExpandState() {
        viewModelScope.launch {
            _isInputDrawerExpanded.value = !_isInputDrawerExpanded.value
        }
    }

    // Function to handle Floating Button Action
    fun floatingButtonAction(navHostController: NavHostController) {
        val navState = navHostController.currentBackStackEntry?.destination?.route == ScreenNav.HomeScreen.name

        when {
            // Case 1: Button is collapsed and already on HomeScreen -> Expand it
            !_isInputDrawerExpanded.value && navState -> {
                toggleExpandState()
            }

            // Case 2: Not on HomeScreen -> Navigate to HomeScreen and expand
            !navState -> {
                navHostController.navigate(ScreenNav.HomeScreen.name) {
                    launchSingleTop = true
                    restoreState = true
                }
                toggleExpandState()
            }

            // Case 3: Button is expanded and already on HomeScreen -> Save the quote
            _isInputDrawerExpanded.value && navState -> {
               // saveQuote()
            }
        }
    }
}