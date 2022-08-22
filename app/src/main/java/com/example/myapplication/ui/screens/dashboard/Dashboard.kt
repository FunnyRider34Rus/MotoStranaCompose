package com.example.myapplication.ui.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.white
import androidx.compose.material.Text as Text

@Composable
fun Dashboard(navController: NavHostController = rememberNavController()) {

    var state by remember { mutableStateOf(0) }
    val titles = listOf(stringResource(id = R.string.news), stringResource(id = R.string.events))

    Column {
        TabRow(
            selectedTabIndex = state,
            backgroundColor = white,
            contentColor = black
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title) }
                )
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