package com.example.magicquote.navigation

sealed class ScreenNav(val route: String) {
    object HomeScreen : ScreenNav("Magic Quote")
    object NewQuoteScreen : ScreenNav("Really Interesting!!")
    object ProfileScreen : ScreenNav("profile_screen")
    object SettingScreen : ScreenNav("settings")
}
