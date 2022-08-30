package com.example.myapplication.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.database.NODE_EVENT
import com.example.myapplication.database.NODE_NEWS
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.white
import androidx.compose.material.Text as Text

@Composable
fun Dashboard(
    navController: NavHostController = rememberNavController()) {

    var state by remember { mutableStateOf(0) }
    val titles = listOf(stringResource(id = R.string.news), stringResource(id = R.string.events))
    val events by remember { mutableStateOf(EventsViewModel()) }

    events.getEvents(NODE_NEWS)

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
                            0 -> events.getEvents(NODE_NEWS)
                            1 -> events.getEvents(NODE_EVENT)
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
            itemsIndexed(events.events) { _, item ->
                Card(modifier = Modifier.padding(horizontal = 0.dp, vertical = 16.dp)) {
                    Column {
                        AsyncImage(
                            model = item.image,
                            contentDescription = null)
                        Text(text = item.title_text, style = MaterialTheme.typography.h1)
                        Text(text = item.header_text, style = MaterialTheme.typography.h3)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    MyApplicationTheme {
        Dashboard(navController = rememberNavController())
    }
}