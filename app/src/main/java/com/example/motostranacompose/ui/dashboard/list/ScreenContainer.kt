package com.example.motostranacompose.ui.dashboard.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.motostranacompose.core.components.BottomBar
import com.example.motostranacompose.navigation.NavGraph

@Composable
fun ScreenContainer(navController: NavHostController, startDestination: String) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomBar(navController = navController) }
    ) { paddingValue ->
        NavGraph(navController = navController, modifier = Modifier.padding(paddingValue), startDestination = startDestination)
    }
}