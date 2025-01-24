package com.example.magicquote

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.magicquote.components.HeaderBlock
import com.example.magicquote.components.MagicLayout
import com.example.magicquote.navigation.MagicNav
import com.example.magicquote.navigation.ScreenNav
import com.example.magicquote.quoteViewModel.MagicViewModel
import com.example.magicquote.ui.theme.MagicQuoteTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //Restriction to Screen Capture
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE)
        // Set the status bar color to black
        window.statusBarColor = android.graphics.Color.BLACK

        setContent {
            val viewModel: MagicViewModel = viewModel()
            MagicQuoteTheme {
                CombinedFrame(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CombinedFrame(viewModel: MagicViewModel){
    val navController: NavHostController = rememberNavController()
    val color= Color(0xFF36443a)
    MagicLayout(topBar = { HeaderBlock(title = { Text(text = "Magic Quote",
        color =
        //color,
        colorResource(R.color.title_Color_Header),
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,

        fontFamily = FontFamily(Font( R.font.calligraffitti)))
        }
    )},
        floatingActionButton = {
            FloatingActionButton(onClick = {viewModel.floatingButtonAction(navHostController =navController )}) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        content = {
        MagicNav(viewModel = viewModel,
            paddingValues = it,
            navController = navController,
            startDestination = ScreenNav.HomeScreen.name)
    })
}
