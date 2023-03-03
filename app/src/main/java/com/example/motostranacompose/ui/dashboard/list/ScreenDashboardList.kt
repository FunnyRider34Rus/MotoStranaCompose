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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.motostranacompose.core.components.ProgressBar
import com.example.motostranacompose.ui.dashboard.list.components.DashboardList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenDashboardList(
    navController: NavController,
    listViewModel: DashboardListViewModel = viewModel()
) {
    val viewState = listViewModel.viewState.collectAsState(DashboardListViewState())
    if (viewState.value.isLoading) ProgressBar()

    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = lazyListState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
        ) {
        items(viewState.value.content) { item ->
            DashboardList(modifier = Modifier.fillParentMaxHeight(), content = item)
        }
    }
}