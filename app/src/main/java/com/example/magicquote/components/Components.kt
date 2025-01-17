package com.example.magicquote.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun MagicLayout(modifier: Modifier = Modifier,
                topBar: @Composable () -> Unit = {},
                bottomBar: @Composable () -> Unit = {},
                snackbarHost: @Composable () -> Unit = {},
                floatingActionButton: @Composable () -> Unit = {},
                floatingActionButtonPosition: FabPosition = FabPosition.End,
                containerColor: Color = MaterialTheme.colorScheme.background,
                contentColor: Color = contentColorFor(containerColor),
                contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
                content: @Composable (PaddingValues) -> Unit){
    Scaffold(
        modifier =modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = content
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderBlock(title: @Composable () -> Unit,
                modifier: Modifier = Modifier,
                navigationIcon: @Composable () -> Unit = {},
                actions: @Composable RowScope.() -> Unit = {},
                expandedHeight: Dp = TopAppBarDefaults.TopAppBarExpandedHeight,
                windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
                colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                scrollBehavior: TopAppBarScrollBehavior? = null){
    CenterAlignedTopAppBar(title,
        modifier,
        navigationIcon,
        actions,
        expandedHeight,
        windowInsets,
        colors,
        scrollBehavior)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDrawer( onDismissRequest: () -> Unit,
                 modifier: Modifier = Modifier,
                 sheetState: SheetState = rememberModalBottomSheetState(),
                 sheetMaxWidth: Dp = BottomSheetDefaults.SheetMaxWidth,
                 shape: Shape = BottomSheetDefaults.ExpandedShape,
                 containerColor: Color = BottomSheetDefaults.ContainerColor,
                 contentColor: Color = contentColorFor(containerColor),
                 tonalElevation: Dp = 0.dp,
                 scrimColor: Color = BottomSheetDefaults.ScrimColor,
                 dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
                 contentWindowInsets: @Composable () -> WindowInsets = { BottomSheetDefaults.windowInsets },
                 properties: ModalBottomSheetProperties = ModalBottomSheetDefaults.properties,
                 content: @Composable ColumnScope.() -> Unit,){

    ModalBottomSheet(onDismissRequest,
        modifier,
        sheetState,
        sheetMaxWidth,
        shape,
        containerColor,
        contentColor,
        tonalElevation,
        scrimColor,
        dragHandle,
        contentWindowInsets,
        properties,
        content)
}