package com.example.myapplication.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.common.AnimatedIndicator
import com.example.myapplication.common.ShowLoading
import com.example.myapplication.common.initUser
import com.example.myapplication.database.firebase.AUTH
import com.example.myapplication.ui.navigation.AuthScreen
import com.example.myapplication.ui.navigation.DetailScreen
import com.example.myapplication.ui.screens.BottomNavigationMenu
import com.example.myapplication.ui.screens.dashboard.model.DashboardEvent
import com.example.myapplication.ui.screens.dashboard.model.DashboardViewState
import com.example.myapplication.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Dashboard(
    navController: NavController, viewModel: DashboardViewModel = viewModel()
) {
    //state
    val viewState = viewModel.viewState.observeAsState(DashboardViewState())
    val scaffoldState = rememberScaffoldState()
    //индекс для активного таб
    var tabIndex by rememberSaveable { mutableStateOf(0) }
    //выбраный элемент события
    var itemIndex by rememberSaveable { mutableStateOf(0) }
    //названия табс
    val titles = listOf(stringResource(id = R.string.news), stringResource(id = R.string.events))
    //идикатор таб
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        AnimatedIndicator(tabPositions = tabPositions, selectedTabIndex = tabIndex)
    }
    //позиция скролла
    val scrollState = rememberLazyListState()

    LaunchedEffect(null) {
        //Проверяем авторизацию
        if (AUTH.currentUser?.uid == null) {
            navController.navigate(AuthScreen.Welcome.route)
        } else {
            initUser()
        }
    }

    when (tabIndex) {
        0 -> viewModel.obtainEvent(DashboardEvent.NewsClicked)
        1 -> viewModel.obtainEvent(DashboardEvent.EventClicked)
    }

    with(viewState.value) {
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = { BottomNavigationMenu(navController = navController) },
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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    state = scrollState,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    items(dashboardValue.size) { item ->
                        Card(
                            onClick = {
                                itemIndex = item
                                viewModel.obtainEvent(
                                    DashboardEvent.ItemClicked(
                                        itemIndex
                                    )
                                )
                                navController.navigate(DetailScreen.Detail.route)
                            },
                            modifier = Modifier.padding(8.dp),
                            backgroundColor = golbat_5,
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column {
                                AsyncImage(
                                    model = dashboardValue[item]?.image,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(max = 288.dp),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.FillWidth
                                )
                                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                                    Text(
                                        text = dashboardValue[item]?.title_text.toString(),
                                        style = MaterialTheme.typography.h1
                                    )
                                    Text(
                                        text = dashboardValue[item]?.header_text.toString(),
                                        modifier = Modifier.padding(vertical = 16.dp),
                                        color = golbat_60,
                                        style = MaterialTheme.typography.h3
                                    )
                                    Text(
                                        text = dashboardValue[item]?.date.toString(),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp),
                                        color = golbat_60,
                                        textAlign = TextAlign.End,
                                        style = MaterialTheme.typography.h5
                                    )
                                }
                            }
                        }
                    }
                }
            }
            if (isLoading) ShowLoading()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    MyApplicationTheme {
        Dashboard(navController = rememberNavController(), viewModel = DashboardViewModel())
    }
}