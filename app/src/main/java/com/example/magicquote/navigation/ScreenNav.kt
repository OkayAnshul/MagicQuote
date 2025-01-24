package com.example.magicquote.navigation

enum class ScreenNav {
    HomeScreen,
    CustomScreen;

    companion object{
        fun fromRoute(route:String?):ScreenNav =
            /*route.substringBefore("/") is used to extract the base part of a route
            (useful for cases where routes contain parameters, e.g., HomeScreen/123).*/

            when(route?.substringBefore("/")){
                HomeScreen.name -> HomeScreen
                CustomScreen.name -> CustomScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognised!")
            }
    }
}