package com.example.motostranacompose.ui.container

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.motostranacompose.core.components.BottomBar
import com.example.motostranacompose.navigation.NavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContainer(
    navController: NavHostController,
    startDestination: String,
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { paddingValue ->
        NavGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValue),
            startDestination = startDestination
        )
    }
}