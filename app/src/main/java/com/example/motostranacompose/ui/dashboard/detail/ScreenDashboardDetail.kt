package com.example.motostranacompose.ui.dashboard.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.motostranacompose.core.components.TopAppBar
import com.example.motostranacompose.ui.dashboard.list.DashboardListViewModel
import com.example.motostranacompose.ui.dashboard.list.DashboardListViewState

@Composable
fun ScreenDashboardDetail(
    navController: NavController,
    listViewModel: DashboardListViewModel = viewModel(),
    id: String
) {
    listViewModel.getDashboardContentByKey(key = id)
    val viewState = listViewModel.viewState.collectAsState(DashboardListViewState())

    Column {
        viewState.value.content?.title?.let {
            TopAppBar(
                modifier = Modifier,
                text = it,
                navigationAction = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.padding(ButtonDefaults.ContentPadding).clickable { navController.navigateUp() }
                    )
                }
            )
        }

    }
}