package com.example.magicquote.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.wear.compose.material3.SwitchButton
import androidx.wear.compose.material3.SwitchButtonColors
import androidx.wear.compose.material3.SwitchButtonDefaults
import com.example.magicquote.datastore.PreferencesKeys
import com.example.magicquote.viewmodel.SettingsViewModel

@Composable
fun SwitchButtonField(
    checked:Boolean,
    onChecked:(Boolean)-> Unit,
    label:String,
    color: SwitchButtonColors = SwitchButtonDefaults
        .switchButtonColors(uncheckedContainerColor = Color.Transparent,
            checkedContainerColor = Color.Transparent
        ),
    shape: Shape
){
    SwitchButton(checked = checked,
        onCheckedChange = onChecked,
        modifier = Modifier.fillMaxSize(),
        label = { Text(label) },
        // secondaryLabel = { Text("Hii")},
        colors = color,
        shape =shape)
}

@Composable
fun OptionalSwitchField(viewModel: SettingsViewModel){
    //Context
    val optionalState =viewModel.uiState.collectAsState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd){
        DropdownMenu(modifier = Modifier, expanded =viewModel.settingExpanded.value,
            onDismissRequest = {viewModel.toggleSettingExpand(false)})
        {
            SwitchButtonField(checked = optionalState.value.contextState,
                onChecked = { viewModel.updateState(PreferencesKeys.contextState,it)},
                label = "Context",
                shape =
                RoundedCornerShape(18).copy(topEnd = CornerSize(2),
                    bottomStart = CornerSize(2)
                )
            )
            SwitchButtonField(checked = optionalState.value.authorState,
                onChecked = { viewModel.updateState(PreferencesKeys.authorState,it)},
                label = "Author",
                shape = RoundedCornerShape(18).copy(topEnd = CornerSize(2),
                    bottomStart = CornerSize(2))
            )
            SwitchButtonField(checked = optionalState.value.categoryState,
                onChecked = { viewModel.updateState(PreferencesKeys.categoryState,it)},
                label = "Category",
                shape = RoundedCornerShape(18).copy(topEnd = CornerSize(2),
                    bottomStart = CornerSize(2))
            )
            SwitchButtonField(checked = optionalState.value.isFavState,
                onChecked = { viewModel.updateState(PreferencesKeys.isFavSTate,it)},
                label = "Fav Button",
                shape = RoundedCornerShape(18).copy(topEnd = CornerSize(2),
                    bottomStart = CornerSize(2))
            )
        }
    }

}