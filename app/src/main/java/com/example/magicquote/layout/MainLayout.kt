package com.example.magicquote.layout

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.magicquote.R
import com.example.magicquote.components.FloatingButton
import com.example.magicquote.components.MagicScaffold
import com.example.magicquote.components.OptionalSwitchField
import com.example.magicquote.components.TopHeader
import com.example.magicquote.navigation.QuoteNav
import com.example.magicquote.navigation.ScreenNav
import com.example.magicquote.viewmodel.QuoteViewModel
import com.example.magicquote.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(navHostController: NavHostController,
               viewModel: QuoteViewModel,
               settingsViewModel: SettingsViewModel
){
    // val viewModel:QuoteViewModel = hiltViewModel()
    //val settingsViewModel:SettingsViewModel = hiltViewModel()
    val currentRoute = navHostController.currentBackStackEntryAsState().value?.destination?.route

    MagicScaffold(topBar = {
        TopHeader(
        title = {
            Text(viewModel.getTopHeader(currentRoute), color = Color.Black) },
        navigationIcon = {

        },
            actions = {
                if(viewModel.isNewScreen(currentRoute))
                {
                    IconButton(onClick ={
                        settingsViewModel.toggleSettingExpand(true)

                    }) {
                        Icon(Icons.Outlined.KeyboardArrowDown,"")
                        OptionalSwitchField(settingsViewModel)
                    }
                }
            },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = colorResource(R.color.Top_header))
        ) },
        floatingActionButton = {

            FloatingButton(onClick = {
                viewModel.floatingButtonAction(currentRoute,
                {
                    navHostController.navigate(ScreenNav.NewQuoteScreen.route)
                },
                {
                        viewModel.addQuote(){
                            navHostController.popBackStack()
                        }
                }
            )
                                     },
                shape = CircleShape,
                containerColor = colorResource(R.color.Light_green)) {
                Icon(Icons.Outlined.Add, "Add Quote")
            }
        },
        containerColor = colorResource(R.color.App_background)){
        paddingValues->
        QuoteNav(navHostController,paddingValues,viewModel,settingsViewModel)
    }
}