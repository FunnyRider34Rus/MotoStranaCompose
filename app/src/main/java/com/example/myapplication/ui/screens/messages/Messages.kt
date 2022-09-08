package com.example.myapplication.ui.screens.messages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.common.utils.LocationViewModel
import com.example.myapplication.common.AnimatedIndicator
import com.example.myapplication.ui.screens.BottomNavigationMenu
import com.example.myapplication.ui.screens.messages.model.MessagesEvent
import com.example.myapplication.ui.screens.messages.model.MessagesViewState
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MissingPermission")
@Composable
fun Messages(navController: NavController, viewModel: MessagesViewModel = viewModel(), locationViewModel: LocationViewModel = viewModel()) {

    //State
    val viewState = viewModel.viewState.observeAsState(MessagesViewState())
    val location = locationViewModel.getLocationLiveData().observeAsState()
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberLazyListState()
    //Tabs
    var tabIndex by rememberSaveable { mutableStateOf(0) }
    val cityName by rememberSaveable { mutableStateOf(location.value?.city) }
    val stateName by rememberSaveable { mutableStateOf(location.value?.state) }

    when (tabIndex) {
        0 -> viewModel.obtainEvent(MessagesEvent.StateClicked(stateName))
        1 -> viewModel.obtainEvent(MessagesEvent.CityClicked(cityName))
    }

    val titles = listOf(stateName, cityName)
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        AnimatedIndicator(
            tabPositions = tabPositions,
            selectedTabIndex = tabIndex
        )
    }

    with(viewState.value) {
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = { BottomNavigationMenu(navController = navController) },
            snackbarHost = { scaffoldState.snackbarHostState }
        ) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                //Табсы с чатом области или города
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
                                    text = title.toString(),
                                    color = black
                                )
                            }
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    state = scrollState,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    items(messages.size) { item ->

                    }
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