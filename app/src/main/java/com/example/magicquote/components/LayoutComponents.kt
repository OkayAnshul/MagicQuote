package com.example.magicquote.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun MagicScaffold(
    topBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    containerColor: Color,
    content: @Composable (PaddingValues) -> Unit )
{
Scaffold(topBar = topBar,
    floatingActionButton = floatingActionButton,
    containerColor =containerColor,
    content = content)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeader(title: @Composable () -> Unit,
              navigationIcon: @Composable () -> Unit = {},
              colors: TopAppBarColors,
              actions: @Composable RowScope.() -> Unit
){
    CenterAlignedTopAppBar(
        title = title,
       // modifier = TODO(),
        navigationIcon = navigationIcon,
        colors = colors,
        actions = actions

    )
}
@Composable
fun FloatingButton(onClick: () -> Unit,
                   shape: Shape ,
                   containerColor: Color,
                   elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(20.dp,30.dp),
                   content: @Composable () -> Unit){
    FloatingActionButton(onClick = onClick,
        shape = shape,
        containerColor = containerColor,
        elevation = elevation,
        content = content)
}
