package com.example.motostranacompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.motostranacompose.ui.MainScreen
import com.example.motostranacompose.ui.authentication.AuthScreen
import com.example.motostranacompose.ui.authentication.AuthViewModel
import com.example.motostranacompose.ui.dashboard.list.DashboardListScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.MAIN) {
            MainScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN = "main_graph"
    const val DETAIL = "detail_graph"
}