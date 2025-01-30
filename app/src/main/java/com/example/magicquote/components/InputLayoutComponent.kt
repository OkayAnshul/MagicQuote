package com.example.magicquote.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.magicquote.R
import com.example.magicquote.viewmodel.QuoteViewModel
import com.example.magicquote.viewmodel.SettingsViewModel



@Composable
fun OutlinedInputCard(viewmodel: QuoteViewModel,
                      settingViewModel: SettingsViewModel
){
    val optionalState =settingViewModel.uiState.collectAsState()

    OutlinedCard(modifier = Modifier.padding(20.dp)
        //.verticalScroll(scrollState)
        ,
        colors = CardDefaults.outlinedCardColors(containerColor = colorResource(R.color.Light_green3)),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(14)
            .copy(topStart = CornerSize(2),
                bottomEnd = CornerSize(2) )) {
        Column(modifier = Modifier.padding(20.dp)){
            AnimateOutlinedTextField(
                visible = true,
                modifier = Modifier.height(110.dp),
                value = viewmodel.quote.value,
                onValueChange = {viewmodel.updateQuote(it) },
                label = "Quote",
                placeholder = "Enter Quote ^_+",
                isError = false
            )
            AnimateOutlinedTextField(
                visible = optionalState.value.contextState,
                value = viewmodel.content.value,
                onValueChange = {viewmodel.updateContent(it) },
                label = "Context",
                placeholder = "Enter Context(Background) ^_^",
                isError = false
            )
            AnimateOutlinedTextField(
                visible = optionalState.value.authorState,
                value = viewmodel.author.value,
                onValueChange = {viewmodel.updateAuthor(it) },
                label = "Author",
                placeholder = "Enter name (╹ڡ╹ )",
                isError = false
            )
            AnimatedCategoryInputField(
                categoryList = viewmodel.quoteCategories,
                visible =optionalState.value.categoryState,
                value = viewmodel.category.value,
                onValueChange = {viewmodel.updateCategory(it)},
                label = "Category",
                placeholder = "Either your own (¬‿¬)",
                isError = false,
                onClick = {
                    viewmodel.updateCategory(it)
                }
            )
        }

    }
}
@Composable
fun AnimateOutlinedTextField(
    visible:Boolean,
    modifier:Modifier=Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    isError:Boolean
){
    AnimatedVisibility(visible = visible) {
        OutlinedTextField(value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = true,
            label = { Text(label)},
            placeholder ={ Text(placeholder)},
            isError = isError,
            //supportingText = { Text(supportingText)},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.title_Color_Header)),
            shape = RoundedCornerShape(18)
                .copy(topStart = CornerSize(2),
                    bottomEnd = CornerSize(2) )
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedCategoryInputField(
    categoryList: List<String>,
    visible:Boolean,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    isError:Boolean,
    onClick:(String) -> Unit
){
    AnimatedVisibility(visible = visible) {
        // Category Outlined Text Field with Drop Down
        val menuExpand = remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = menuExpand.value,
            onExpandedChange = { menuExpand.value =! menuExpand.value }
        ) {

            OutlinedTextField(
                value = value,
                onValueChange =onValueChange,
                label = { Text(label) },
                singleLine = true,
                isError = isError,
                placeholder = {
                    Text(placeholder)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.title_Color_Header)),
                shape = RoundedCornerShape(18)
                    .copy(topStart = CornerSize(2),
                        bottomEnd = CornerSize(2)),
                //trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dexpanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = menuExpand.value,
                onDismissRequest = { menuExpand.value = false }
            ) {
                categoryList.forEach { qC ->
                    DropdownMenuItem(
                        text = {
                            Text(qC)},
                        onClick = {
                            onClick(qC)
                            menuExpand.value =false
                        }
                    )
                }
            }
        }
    }
}
@Composable
fun OutlinedInputButton(viewmodel: QuoteViewModel,
                        settingViewModel: SettingsViewModel,
                        onSave:() -> Unit,
                        onClose:() -> Unit){
    val optionalState =settingViewModel.uiState.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        IconButton(
            onClick = {
                onClose()
            },

        ) {

            Icon(
                Icons.Rounded.Close, "Close Icon",
                tint = colorResource(R.color.Dark_green)
            )

        }

        ElevatedButton(
            onClick = {
                onSave()
            },
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 12.dp,
                pressedElevation = 14.dp,
                disabledElevation = 0.dp
            ), colors = ButtonDefaults
                .elevatedButtonColors(containerColor = colorResource(R.color.Light_green))
        ) {
            Text("Save Bitchh🐾")
        }
        AnimatedFavButtonField(visible = optionalState.value.isFavState,
            checked = viewmodel.isFav.value,
            onChecked = {viewmodel.toggleFavorite()})
    }
}
 @Composable
fun AnimatedFavButtonField(visible: Boolean,
                           checked:Boolean,
                           onChecked:(Boolean)->Unit){
     AnimatedVisibility(visible =visible) {
         IconToggleButton(
             checked =checked,
             onCheckedChange = onChecked,
         ) {

             Icon(
                 imageVector = if (checked) Icons.Default.Favorite else Icons.Filled.FavoriteBorder,
                 contentDescription = if (checked) "Remove from favorites" else "Add to favorites",
                 tint = if (checked) colorResource(R.color.Dark_green) else colorResource(
                     R.color.Dark_green
                 )
             )
         }
     }
}
