package com.example.magicquote.screenLayout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.magicquote.R
import com.example.magicquote.components.InputDrawer
import com.example.magicquote.components.InputField
import com.example.magicquote.dataBase.Quote
import com.example.magicquote.dummy
import com.example.magicquote.quoteViewModel.MagicViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfflineQuote(viewModel: MagicViewModel) {
    val scope = rememberCoroutineScope()
    val sheetState= rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val quote by viewModel._magicList.collectAsState(emptyList())
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 2.dp,
            horizontal = 6.dp)
    ) {
        LazyColumn(modifier = Modifier) {
            items(quote) { quote->
                QuoteRow(
                    quote =quote.text,
                    onClickDelete = {
                        viewModel.removeQuote(quote)
                    },
                    onClickEdit = {
                    },
                )
            }
        }
    }
    if (viewModel.isInputDrawerExpanded.value) {
    InputDrawer(
        //modifier = Modifier.verticalScroll(rememberScrollState()),
        onDismissRequest = {
            scope.launch {
                sheetState.hide()
                viewModel.toggleExpandState()
            }
        },
        sheetState = sheetState
    ) {
            InputField(
                onClickClose = {
                    scope.launch {
                        sheetState.hide()
                        viewModel.toggleExpandState()
                    } },
                onClickSave = {quote ->
                    scope.launch {
                        viewModel.addQuote(quote)
                        sheetState.hide()
                        viewModel.toggleExpandState()
                    }
                }
            )


    }
    }
}
@Composable
fun QuoteRow(
    quote: String,
    description: String?=null,
    modifier: Modifier = Modifier,
    onClickDelete: () -> Unit,
    onClickEdit: () -> Unit = {}
) {
    val backgroundColor = colorResource(R.color.Light_green)
//        Color(0xFFDFF5E3) // Light green
    val accentColor = colorResource(R.color.Dark_green)
    //Color(0xFF4CAF50) // Darker green
    val textColor = colorResource(R.color.Dark_text)
    //Color(0xFF1B1B1B) // Dark text
    val secondaryTextColor = colorResource(R.color.Muted_gray_green)
    //Color(0xFF6D6D6D) // Muted gray-green

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClickDelete() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Circular Accent
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(accentColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "“",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                // Quote
                Text(
                    text = quote,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = textColor
                    ),
                    // maxLines = 2
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Description
                if (description != null) {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            color = secondaryTextColor
                        ),
                        maxLines = 3
                    )
                }
            }
        }
    }
}
