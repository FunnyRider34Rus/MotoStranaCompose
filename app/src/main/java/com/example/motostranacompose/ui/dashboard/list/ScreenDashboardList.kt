package com.example.motostranacompose.ui.dashboard.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.motostranacompose.R
import com.example.motostranacompose.core.components.ProgressBar
import com.example.motostranacompose.core.components.TopAppBar
import com.example.motostranacompose.ui.dashboard.list.components.DashboardListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenDashboardList(
    navController: NavController,
    modifier: Modifier,
    viewModel: DashboardListViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState(DashboardListViewState())
    if (viewState.isLoading) ProgressBar()

    val lazyListState = rememberLazyListState()

    Column(modifier = modifier) {
        TopAppBar(modifier = Modifier, text = stringResource(id = R.string.bottombar_lable_dashboard), navigationAction = {  }) {
            IconButton(onClick = { viewModel.obtainEvent(DashboardListEvent.ButtonAddClick) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
        ) {
            items(viewState.contents) { item ->
                DashboardListItem(
                    navController = navController,
                    modifier = Modifier.fillParentMaxHeight(),
                    content = item
                )
            }
        }
    }
}