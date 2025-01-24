package com.example.magicquote.quoteViewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.magicquote.dataBase.Quote
import com.example.magicquote.dataBase.QuoteRepository
import com.example.magicquote.dummy
import com.example.magicquote.navigation.ScreenNav
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MagicViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel() {

    // State for input drawer expansion
    private val _isInputDrawerExpanded = mutableStateOf(true)
    val isInputDrawerExpanded: State<Boolean> get() = _isInputDrawerExpanded

    // Toggles the expand state
    fun toggleExpandState() {
        _isInputDrawerExpanded.value = !_isInputDrawerExpanded.value
    }

    // Function to handle Floating Button Action
    fun floatingButtonAction(navHostController: NavHostController) {
        val navState =
            navHostController.currentBackStackEntry?.destination?.route == ScreenNav.HomeScreen.name

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
    // Flow to hold the list of quotes
    //val magicList: StateFlow<List<Quote>> get() = _magicList.asStateFlow()
     val _magicList: StateFlow<List<Quote>> = repository.getAllQuote()
                .distinctUntilChanged()
                .stateIn (viewModelScope,
                SharingStarted.Lazily,
                emptyList() )

    fun addQuote(quote: Quote){
        viewModelScope.launch {
            repository.addQuote(quote)
        }
    }
    fun removeQuote(quote: Quote){
        viewModelScope.launch {
            repository.removeQuote(quote)
        }
    }
    fun removeAllQuote(){
        viewModelScope.launch {
            repository.removeAll()
        }
    }
    fun updateQuote(quote: Quote){
        viewModelScope.launch {
            repository.updateQuote(quote)
        }
    }
    fun getById(id:Long){
        viewModelScope.launch {
            repository.getQuoteById(id)
        }
    }
     fun insertDummy(){
    }
//    // Function to save a quote (Dummy function for now)
//    private fun saveQuote() {
//        // Implement saving logic here
//        println("Saving the quote!")
//    }
}
