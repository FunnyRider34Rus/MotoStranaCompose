package com.example.myapplication.ui.screens.dashboard

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.screens.BottomNavigationMenu
import com.example.myapplication.ui.screens.dashboard.model.DashboardEvent
import com.example.myapplication.ui.screens.dashboard.model.DashboardViewState
import com.example.myapplication.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Dashboard(
    navController: NavController, viewModel: DashboardViewModel = viewModel()
) {
    //state
    val viewState = viewModel.viewState.observeAsState(DashboardViewState())
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
    //состояние bottomSheet
    val stateSheet = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()


    when (tabIndex) {
        0 -> viewModel.obtainEvent(DashboardEvent.NewsClicked)
        1 -> viewModel.obtainEvent(DashboardEvent.EventClicked)
    }

    LaunchedEffect(null) {
        stateSheet.bottomSheetState.collapse()
    }

    with(viewState.value) {
        BottomSheetScaffold(
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
                Column(modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())) {
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
            modifier = Modifier.padding(top = 2.dp),
            scaffoldState = stateSheet,
            sheetShape = RoundedCornerShape(24.dp),
            sheetPeekHeight = 0.dp
        ) {
            Scaffold(
                bottomBar = { BottomNavigationMenu(navController = navController) },
                backgroundColor = golbat_10
            ) { paddingValues ->
                Column(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    //Табсы с новостями и мероприятиями
                    TabRow(
                        selectedTabIndex = tabIndex,
                        modifier = Modifier.height(88.dp),
                        backgroundColor = white,
                        contentColor = black,
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
                                        style = MaterialTheme.typography.h1
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
                                    viewModel.obtainEvent(DashboardEvent.ItemClicked(itemIndex))
                                    scope.launch {
                                        stateSheet.bottomSheetState.animateTo(
                                            BottomSheetValue.Expanded,
                                            tween(800)
                                        )
                                    }
                                },
                                modifier = Modifier.padding(horizontal = 8.dp,vertical = 8.dp),
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

@Composable
fun Indicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .border(
                width = 126.dp,
                color = black,
                shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Divider(
            modifier = Modifier
                .height(6.dp)
                .border(
                    width = 26.dp,
                    color = black,
                    shape = RoundedCornerShape(4.dp)
                ),
            color = white
        )
    }
}

@Composable
fun AnimatedIndicator(tabPositions: List<TabPosition>, selectedTabIndex: Int) {
    val transition = updateTransition(selectedTabIndex, label = "")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        }, label = ""
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }, label = ""
    ) {
        tabPositions[it].right
    }

    Indicator(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorStart + 8.dp)
            .width((indicatorEnd - 16.dp) - indicatorStart)
    )
}