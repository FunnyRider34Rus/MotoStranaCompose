package com.example.motostranacompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.motostranacompose.ui.dashboard.list.DashboardListScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.dashboard_list.route
    ) {
        composable(route = BottomBarScreen.dashboard_list.route) {
            DashboardListScreen(navController = navController)
        }
    }
}