package com.example.magicquote.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.magicquote.R
import com.example.magicquote.database.QuoteTemplate
import com.example.magicquote.viewmodel.QuoteViewModel

@Composable
fun SavedQuoteScreen(viewModel: QuoteViewModel = hiltViewModel(),
                     navHostController: NavHostController){
    val lazyState = rememberLazyListState()
    val quoteList by viewModel.magicList.collectAsState()
    LazyColumn(state = lazyState,
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp, vertical = 2.dp)){
        items(quoteList,
            key = { quote -> quote.id }){
            QuoteRow(it,
                onClickDelete = {viewModel.removeQuote(it)},
                onClickEdit = {})
        }
    }
}


@Composable
fun QuoteRow(quote: QuoteTemplate,
             onClickDelete:()->Unit,
             onClickEdit:()-> Unit){
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp,vertical = 4.dp)
            .clickable { onClickDelete() }
        ,
        colors = CardDefaults.outlinedCardColors(containerColor =colorResource(R.color.Light_green3)),
        border = BorderStroke(1.dp, Color.Black),
           // .cardColors(),
        shape = RoundedCornerShape(26)
            .copy(topStart = CornerSize(2),
                bottomEnd = CornerSize(2) )
    ){
        Column(modifier = Modifier.padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        { Text(quote.quote)  }

    }
}
