package com.example.motostranacompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.motostranacompose.core.components.BottomBar
import com.example.motostranacompose.navigation.BottomNavGraph

@Composable
fun ScreenMain() {
    val navController: NavHostController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomBar(navController = navController) }
    ) { paddingValue ->
        Box(modifier = Modifier.padding(paddingValue).fillMaxSize()) {
            BottomNavGraph(navController = navController)
        }
    }
}