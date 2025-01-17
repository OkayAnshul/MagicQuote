package com.example.magicquote.screenLayout

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import com.example.magicquote.components.InputDrawer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfflineQuote() {
    val sheetState= rememberModalBottomSheetState()
    InputDrawer(
        onDismissRequest ={},
        sheetState = sheetState,
    ) { }
}