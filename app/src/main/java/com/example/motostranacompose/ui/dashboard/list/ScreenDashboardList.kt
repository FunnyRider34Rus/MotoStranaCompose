package com.example.motostranacompose.ui.dashboard.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.motostranacompose.core.components.ProgressBar
import com.example.motostranacompose.ui.dashboard.list.components.DashboardListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenDashboardList(
    navController: NavController,
    modifier: Modifier,
    listViewModel: DashboardListViewModel = hiltViewModel()
) {
    val viewState = listViewModel.viewState.collectAsState(DashboardListViewState())
    if (viewState.value.isLoading) ProgressBar()

    val lazyListState = rememberLazyListState()

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
        ) {
            items(viewState.value.contents) { item ->
                DashboardListItem(
                    navController = navController,
                    modifier = Modifier.fillParentMaxHeight(),
                    content = item
                )
            }
        }
}