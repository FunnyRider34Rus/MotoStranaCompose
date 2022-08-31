package com.example.myapplication.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.screens.dashboard.model.DashboardEvent
import com.example.myapplication.ui.screens.dashboard.model.DashboardViewState
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.white
import androidx.compose.material.Text as Text

@Composable
fun Dashboard(
    viewModel: DashboardViewModel
) {

    var state by remember { mutableStateOf(0) }
    val titles = listOf(stringResource(id = R.string.news), stringResource(id = R.string.events))
    val viewState = viewModel.viewState.observeAsState(DashboardViewState())

    Column {
        //Табсы с новостями и мероприятиями
        TabRow(
            selectedTabIndex = state,
            backgroundColor = white,
            contentColor = black
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = {
                        state = index
                        when (state) {
                            0 -> viewModel.obtainEvent(DashboardEvent.NewsClicked)
                            1 -> viewModel.obtainEvent(DashboardEvent.EventClicked)
                        }
                    },
                    text = { Text(text = title) }
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            itemsIndexed(viewState.value.dashboardValue) { _, item ->
                Card(modifier = Modifier.padding(horizontal = 0.dp, vertical = 16.dp)) {
                    Column {
                        AsyncImage(
                            model = item?.image,
                            contentDescription = null
                        )
                        Text(
                            text = item?.title_text.toString(),
                            style = MaterialTheme.typography.h1
                        )
                    }
                    Text(
                        text = item?.header_text.toString(),
                        style = MaterialTheme.typography.h3
                    )
                }
            }
        }
    }
}