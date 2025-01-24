package com.example.magicquote.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.magicquote.R
import com.example.magicquote.dataBase.Quote


@Composable
fun MagicLayout(modifier: Modifier = Modifier,
                topBar: @Composable () -> Unit = {},
                bottomBar: @Composable () -> Unit = {},
                snackbarHost: @Composable () -> Unit = {},
                floatingActionButton: @Composable () -> Unit = {},
                floatingActionButtonPosition: FabPosition = FabPosition.End,
                //containerColor: Color = MaterialTheme.colorScheme.background,
                containerColor: Color =colorResource(R.color.App_background) ,
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
                //colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.Light_green3),
                    //colorResource(id = R.color.Top_header), // Color for the app bar background
                     scrolledContainerColor = Color.Transparent, // Color when the app bar scrolls (optional)
                     navigationIconContentColor = Color.White, // Color for navigation icons
                     titleContentColor = Color.Black, // Color for the title text
                     actionIconContentColor = Color.White // Color for action icons
),
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

@Composable
fun InputField(
    contextEnabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    quoteIsError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
              // interactionSource: MutableInteractionSource? = null,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colorsTextField: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    colorsCard: CardColors = CardDefaults
        .elevatedCardColors(containerColor = colorResource(R.color.Light_green3)) ,
    //colorsCard: CardColors = CardDefaults.elevatedCardColors(),
    onClickSave:(quote:Quote) -> Unit,
    onClickClose:() -> Unit={}){

    val quote = remember { mutableStateOf<String>("") }
    var qContext = remember { mutableStateOf<String?>("") }

    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(6.dp),
        colors = colorsCard,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 12.dp,
            disabledElevation = 0.dp
        )){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = {}) {
                Text("Deep Thoughts, haan?")
            }
            //Quote Field
            OutlinedTextField(
                quote.value,
                {quote.value = it },
                textStyle = textStyle,
                label = {
                    Text("Quote")
                },
                placeholder = {
                    Text("Enter your Quote ^_^")
                },
                leadingIcon = {
                    Icon(
                        ImageVector.vectorResource(id = R.drawable.baseline_brush_24),
                        "quote Icon"
                    )
                },
//                trailingIcon = {},
//                suffix = {},
//                prefix = {},
                // supportingText = {},
                isError = quoteIsError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                shape = shape,
                colors = colorsTextField
            )

            //Spacer(modifier = Modifier.padding())

            //Context Field
            qContext.value?.let { qc ->
                OutlinedTextField(
                    value = qc,
                    onValueChange = {qContext.value =it},
                    modifier = Modifier,
                    enabled = contextEnabled,
                    textStyle = textStyle,
                    label = {
                        Text("Context")
                    },
                    placeholder = {
                        Text("Enter Context")
                    },
                    leadingIcon = {
                        Icon(
                            ImageVector.vectorResource(id = R.drawable.baseline_content_paste_go_24),
                            "quote Icon"
                        )
                    },
        //                trailingIcon = {},
        //                suffix = {},
        //                prefix = {},
                    supportingText = {
                        Text("(Optional)",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Left)
                    },
                    isError = false,
                    visualTransformation = visualTransformation,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    shape = shape,
                    colors = colorsTextField
                )
            }

            Row(modifier=Modifier
                .fillMaxWidth()
                .padding(2.dp),
                horizontalArrangement = Arrangement.Absolute.Center
            ) {

                ElevatedButton(
                    onClick = {
                        onClickClose()
                        qContext.value = ""
                        quote.value = ""
                              },
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 12.dp,
                        pressedElevation = 14.dp,
                        disabledElevation = 0.dp
                    ),
                    colors = ButtonDefaults
                        .elevatedButtonColors(containerColor = colorResource(R.color.Light_green))
                ) {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Icon(
                        Icons.Default.Close, "Close Icon",
                        //  tint = Color.Red
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
                Spacer(modifier = Modifier.padding(12.dp))
                ElevatedButton(onClick = {
                    onClickSave(Quote(text = quote.value, context = qContext.value))
                    qContext.value = ""
                    quote.value = ""
                                         },
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 12.dp,
                        pressedElevation = 14.dp,
                        disabledElevation = 0.dp
                    ),colors = ButtonDefaults
                            .elevatedButtonColors(containerColor = colorResource(R.color.Light_green))
                ) {
                    Text("Save Bitchh🐾")
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDrawer(onDismissRequest: () -> Unit,
                modifier: Modifier = Modifier
                    //.verticalScroll(rememberScrollState())
                        ,
                sheetState: SheetState = rememberModalBottomSheetState(),
                sheetMaxWidth: Dp = BottomSheetDefaults.SheetMaxWidth,
                shape: Shape = BottomSheetDefaults.ExpandedShape,
                containerColor: Color = colorResource(R.color.Light_green1),
                 //containerColor: Color = BottomSheetDefaults.ContainerColor,
                contentColor: Color = contentColorFor(containerColor),
                tonalElevation: Dp = 0.dp,
                scrimColor: Color = BottomSheetDefaults.ScrimColor,
                dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
                contentWindowInsets: @Composable () -> WindowInsets = { BottomSheetDefaults.windowInsets },
                properties: ModalBottomSheetProperties = ModalBottomSheetDefaults.properties,
                content: @Composable ColumnScope.() -> Unit,
                 ){

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