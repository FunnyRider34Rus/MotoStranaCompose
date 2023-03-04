package com.example.motostranacompose.ui.dashboard.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.motostranacompose.core.components.TopAppBar
import com.example.motostranacompose.ui.dashboard.list.DashboardListViewModel
import com.example.motostranacompose.ui.dashboard.list.DashboardListViewState
import com.google.accompanist.web.rememberWebViewState

@Composable
fun ScreenDashboardDetail(
    navController: NavController,
    listViewModel: DashboardListViewModel = viewModel(),
    id: String
) {
    listViewModel.getDashboardContentByKey(key = id)
    val viewState = listViewModel.viewState.collectAsState(DashboardListViewState())
    val state = viewState.value.content?.body?.let { rememberWebViewState(it) }
    val context = LocalContext.current
    Column {
        viewState.value.content?.title?.let { TopAppBar(modifier = Modifier, text = it) }

    }
}