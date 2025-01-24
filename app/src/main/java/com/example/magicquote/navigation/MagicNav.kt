package com.example.magicquote.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.magicquote.quoteViewModel.MagicViewModel
import com.example.magicquote.screenLayout.OfflineQuote
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun MagicNav(
    viewModel: MagicViewModel,
    startDestination: String,
    navController: NavHostController,
    paddingValues: PaddingValues,
    contentAlignment: Alignment = Alignment.TopStart,
    route: String? = null,
    /* enterTransition:
    (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
        {
            fadeIn(animationSpec = tween(700))
        },
    exitTransition:
    (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
        {
            fadeOut(animationSpec = tween(700))
        },
    popEnterTransition:
    (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
        enterTransition,
    popExitTransition:
    (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
        exitTransition,
    sizeTransform:
    (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> SizeTransform?)? =
        null */
){

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(paddingValues),
        contentAlignment = contentAlignment,
        route = route,
//        enterTransition = enterTransition,
//        exitTransition = exitTransition,
//        popEnterTransition = popEnterTransition,
//        popExitTransition = popExitTransition,
//        sizeTransform = sizeTransform,
    ) {
        /*Here Both NavHost and composable have similar same parameter just to support flexible nature across
         Tiniest piece which have to animate or any thing. */

        composable(ScreenNav.HomeScreen.name){
            OfflineQuote(viewModel,
               // navController = navController
            )
        }
        composable(ScreenNav.CustomScreen.name){
           // Screen2(navController)
        }
    }
}