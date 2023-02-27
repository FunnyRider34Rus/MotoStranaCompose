package com.example.motostranacompose.ui.dashboard.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.motostranacompose.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardListScreen(
    viewModel: DashboardListViewModel = hiltViewModel(),
    navController: NavController
) {
    val viewState = viewModel.viewState.collectAsState(DashboardListViewState())

    Scaffold(
        topBar = { DashboardListTopBar() }
    ) { paddingValue ->

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardListTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    )
}
