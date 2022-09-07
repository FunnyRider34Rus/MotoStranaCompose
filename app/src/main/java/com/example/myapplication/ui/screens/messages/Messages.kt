package com.example.myapplication.ui.screens.messages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.common.AnimatedIndicator
import com.example.myapplication.ui.screens.BottomNavigationMenu
import com.example.myapplication.ui.screens.messages.model.MessagesViewState
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Messages(navController: NavController, viewModel: MessagesViewModel = viewModel()){

    val scaffoldState = rememberScaffoldState()
    val viewState = viewModel.viewState.observeAsState(MessagesViewState())
    val performLocationAction by viewModel.performLocationAction.collectAsState()

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var tabIndex by rememberSaveable { mutableStateOf(0) }
    val titles = listOf("Область", "Город")
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        AnimatedIndicator(
            tabPositions = tabPositions,
            selectedTabIndex = tabIndex
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomNavigationMenu(navController = navController) },
        snackbarHost = { scaffoldState.snackbarHostState }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            //Табсы с новостями и мероприятиями
            TabRow(
                selectedTabIndex = tabIndex,
                backgroundColor = white,
                indicator = indicator
            ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = {
                            tabIndex = index
                        },
                        text = {
                            Text(
                                text = title,
                                color = black
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessagesPreview() {
    MyApplicationTheme {
        Messages(navController = rememberNavController())
    }
}