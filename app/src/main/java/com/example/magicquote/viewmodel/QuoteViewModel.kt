package com.example.magicquote.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magicquote.Dummy
import com.example.magicquote.database.QuoteRepository
import com.example.magicquote.database.QuoteTemplate
import com.example.magicquote.navigation.ScreenNav
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class QuoteViewModel @Inject constructor(private val repository: QuoteRepository) :ViewModel() {

    val quoteCategories = listOf(
        "Inspiration",
        "Motivation",
        "Happiness",
        "Love",
        "Wisdom",
        "Success",
        "Life",
        "Friendship",
        "Positivity",
        "Gratitude",
        "Mindfulness",
        "Humor",
        "Courage",
        "Dreams",
        "Spirituality",
        "Philosophy",
        "Relationships",
        "Leadership",
        "Hard Work",
        "Self-Care",
        "Kindness",
        "Hope",
        "Forgiveness",
        "Adventure",
        "Creativity",
        "Resilience",
        "Patience",
        "Health",
        "Freedom",
        "Family"
    )
    // Private backing properties
    private val _quote = mutableStateOf("")
    private val _content = mutableStateOf("")
    private val _author = mutableStateOf("")
    private val _category = mutableStateOf("")
    private val _isFav = mutableStateOf<Boolean>(false)
    fun resetInputField(){
        _quote.value=""
        _content.value=""
        _author.value=""
        _category.value=""
        _isFav.value=false
    }
    // Public immutable exposed states
    val quote: State<String> = _quote
    val content: State<String> = _content
    val author: State<String> = _author
    val category: State<String> = _category
    val isFav: State<Boolean> = _isFav
    //Update
    fun updateQuote(quote: String) {
        _quote.value = quote  // Correct way to update value
    }
    fun updateContent(content: String) {
        _content.value = content
    }
    fun updateAuthor(author: String) {
        _author.value = author
    }
    fun updateCategory(category: String) {
        _category.value = category
    }
    fun toggleFavorite() {
        _isFav.value =! isFav.value
    }
    fun isNewScreen(currentScreen: String?): Boolean {
        return (currentScreen == ScreenNav.NewQuoteScreen.route)
    }
    fun getTopHeader(currentScreen:String?): String {
         //Return a header string based on the current route
        return when (currentScreen) {
            ScreenNav.HomeScreen.route -> "Magic Quote"
            ScreenNav.NewQuoteScreen.route -> "New Quote✍ (^///^)"
            ScreenNav.SettingScreen.route -> "Setting"
            //ScreenNav.ProfileScreen.route -> "Your Profile"
            else -> "Welcome"
        }
    }
    // Function to handle Floating Button Action
    fun floatingButtonAction(currentScreen: String?,
                             navToNewQuote:()->Unit,
                             saveIfQuote:()->Unit) {
        val navState =
            currentScreen == ScreenNav.NewQuoteScreen.route
        viewModelScope.launch(Dispatchers.Main.immediate){
            when {
                !navState -> {
                    navToNewQuote()
                }
                navState -> {
                    saveIfQuote()
                }
            }
        }

    }


    //Main Database
    // Flow to hold the list of quotes
    private val _magicList: StateFlow<List<QuoteTemplate>> = repository.getAllQuote()
        .distinctUntilChanged()
        .stateIn (viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList() )

    val magicList: StateFlow<List<QuoteTemplate>> get() = _magicList

fun addQuote(quote: QuoteTemplate?=null,
             navBack:()->Unit={}) = viewModelScope.launch(Dispatchers.IO) {
    if(quote!=null){
        repository.addQuote(quote)
    }
    else {
        val newQuote = QuoteTemplate(
            quote = _quote.value,
            context = _content.value,
            author = _author.value,
            category = _category.value,
            isFavorite = _isFav.value
        )
        if (newQuote.quote.isNotEmpty()) {
            repository.addQuote(newQuote)
            resetInputField()
            withContext(Dispatchers.Main) { // Switch to main thread
                navBack()
            }
        }
    }
    }
    fun removeQuote(quoteTemplate: QuoteTemplate) = viewModelScope.launch(Dispatchers.IO) {
        repository.removeQuote(quoteTemplate)
    }
    fun removeAllQuote(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeAll()
        }
    }
    fun updateQuote(quoteTemplate: QuoteTemplate){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateQuote(quoteTemplate)
        }
    }
    fun getById(id:Long){
        viewModelScope.launch(Dispatchers.IO){
            repository.getQuoteById(id)
        }
    }
    fun insertDummy(){
        Dummy.list.forEach {
            addQuote(it)
        }
    }
}