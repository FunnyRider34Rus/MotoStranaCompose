package com.example.myapplication.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.screens.BottomNavigationMenu
import com.example.myapplication.ui.screens.dashboard.model.DashboardEvent
import com.example.myapplication.ui.screens.dashboard.model.DashboardViewState
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.golbat_60
import com.example.myapplication.ui.theme.white
import kotlinx.coroutines.launch
import androidx.compose.material.Text as Text

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Dashboard(
    navController: NavController, viewModel: DashboardViewModel
) {
    var tabIndex by remember { mutableStateOf(0) }
    var index by remember { mutableStateOf(0) }
    val skipHalfExpanded by remember { mutableStateOf(true) }
    val titles = listOf(stringResource(id = R.string.news), stringResource(id = R.string.events))
    val viewState = viewModel.viewState.observeAsState(DashboardViewState())
    val scrollState = rememberLazyListState()

    val stateSheet = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded
    )
    val scope = rememberCoroutineScope()

    when (tabIndex) {
        0 -> viewModel.obtainEvent(DashboardEvent.NewsClicked)
        1 -> viewModel.obtainEvent(DashboardEvent.EventClicked)
    }

    with(viewState.value) {
        ModalBottomSheetLayout(
            sheetContent = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Text(
                        text = itemValue?.title_text.toString(),
                        modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 0.dp),
                        style = MaterialTheme.typography.h1
                    )
                    Text(
                        text = itemValue?.date.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.h5
                    )
                    AsyncImage(
                        model = itemValue?.image,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        text = itemValue?.header_text.toString(),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                        style = MaterialTheme.typography.h2
                    )
                    Text(
                        text = itemValue?.body_text.toString(),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = golbat_60,
                        style = MaterialTheme.typography.h3
                    )
                }
            },
            modifier = Modifier
                .fillMaxSize(),
            sheetState = stateSheet
        ) {
            Scaffold(
                bottomBar = { BottomNavigationMenu(navController = navController) }
            ) { paddingValues ->
                Column (modifier = Modifier.padding(paddingValues)) {
                    //Табсы с новостями и мероприятиями
                    TabRow(
                        selectedTabIndex = tabIndex,
                        backgroundColor = white,
                        contentColor = black
                    ) {
                        titles.forEachIndexed { index, title ->
                            Tab(
                                selected = tabIndex == index,
                                onClick = {
                                    tabIndex = index
                                },
                                text = { Text(text = title) }
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
                        items(viewState.value.dashboardValue.size) { item ->
                            Card(
                                modifier = Modifier.padding(horizontal = 1.dp, vertical = 6.dp),
                                onClick = {
                                    index = item
                                    viewModel.obtainEvent(DashboardEvent.ItemClicked(index))
                                    scope.launch { stateSheet.show() }
                                }) {
                                Column {
                                    AsyncImage(
                                        model = dashboardValue[item]?.image,
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxWidth(),
                                        alignment = Alignment.Center,
                                        contentScale = ContentScale.FillWidth
                                    )
                                    Text(
                                        text = dashboardValue[item]?.title_text.toString(),
                                        modifier = Modifier.padding(horizontal = 16.dp),
                                        style = MaterialTheme.typography.h1
                                    )
                                    Text(
                                        text = dashboardValue[item]?.header_text.toString(),
                                        modifier = Modifier.padding(
                                            horizontal = 8.dp,
                                            vertical = 16.dp
                                        ),
                                        color = golbat_60,
                                        style = MaterialTheme.typography.h3
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}