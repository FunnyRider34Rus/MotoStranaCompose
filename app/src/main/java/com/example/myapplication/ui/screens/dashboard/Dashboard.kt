package com.example.myapplication.ui.screens.dashboard

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.myapplication.ui.theme.golbat_80
import com.example.myapplication.ui.theme.white
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Dashboard(
    navController: NavController, viewModel: DashboardViewModel
) {
    //индекс для активного таб
    var tabIndex by rememberSaveable { mutableStateOf(0) }
    //выбраный элемент события
    var itemIndex by rememberSaveable { mutableStateOf(0) }
    //названия табс
    val titles = listOf(stringResource(id = R.string.news), stringResource(id = R.string.events))
    val viewState = viewModel.viewState.observeAsState(DashboardViewState())
    //позиция скролла
    val scrollState = rememberLazyListState()
    //состояние bottomSheet
    val stateSheet = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()

    when (tabIndex) {
        0 -> viewModel.obtainEvent(DashboardEvent.NewsClicked)
        1 -> viewModel.obtainEvent(DashboardEvent.EventClicked)
    }

    with(viewState.value) {
        ModalBottomSheetLayout(
            sheetContent = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Divider(
                        modifier = Modifier
                            .width(26.dp)
                            .height(6.dp)
                            .border(
                                width = 26.dp,
                                color = golbat_80,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        color = white
                    )
                }
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
            sheetState = stateSheet,
            sheetShape = RoundedCornerShape(0.dp)
        ) {
            Scaffold(
                bottomBar = { BottomNavigationMenu(navController = navController) }
            ) { paddingValues ->
                Column(modifier = Modifier
                    .padding(paddingValues)) {
                    //Табсы с новостями и мероприятиями
                    TabRow(
                        selectedTabIndex = tabIndex,
                        modifier = Modifier.height(88.dp),
                        backgroundColor = white,
                        contentColor = black
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
                                        style = MaterialTheme.typography.h1)
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
                                    viewModel.obtainEvent(DashboardEvent.ItemClicked(itemIndex))
                                    scope.launch { stateSheet.show() }
                                },
                                modifier = Modifier.padding(vertical = 1.dp),
                                shape = RoundedCornerShape(0.dp)
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
                                    Text(
                                        text = dashboardValue[item]?.date.toString(),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                            horizontal = 8.dp,
                                            vertical = 8.dp
                                        ),
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
        }
    }
}