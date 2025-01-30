package com.example.magicquote.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.magicquote.components.OutlinedInputButton
import com.example.magicquote.components.OutlinedInputCard
import com.example.magicquote.viewmodel.QuoteViewModel
import com.example.magicquote.viewmodel.SettingsViewModel


@Composable
fun NewQuoteScreen(viewmodel: QuoteViewModel,
                   settingsViewModel: SettingsViewModel,
                   navHostController: NavHostController){

    val scrollState= rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .requiredHeight(1300.dp),
            //verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            OutlinedInputCard(viewmodel,settingsViewModel)
            OutlinedInputButton(
                viewmodel, settingsViewModel,
                onSave = {
                        viewmodel.addQuote(){
                            navHostController.popBackStack()
                        }
                    },
                onClose = {
                    if(viewmodel.quote.value != "")
                    viewmodel.resetInputField()
                    else
                        navHostController.popBackStack()
                }
            )
            Button(onClick = {viewmodel.insertDummy()}) {
                Text("Dummy?")
            }


        }
    //Button(onClick = {}) { }
}