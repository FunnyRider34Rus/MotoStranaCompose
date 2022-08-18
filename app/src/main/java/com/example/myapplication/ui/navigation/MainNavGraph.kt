package com.example.myapplication.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.BottomBarScreen
import com.example.myapplication.ui.screens.dashboard.Dashboard
import com.example.myapplication.ui.screens.messages.Messages
import com.example.myapplication.ui.screens.settings.Settings

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Dashboard.route
    ) {
        composable(route = BottomBarScreen.Dashboard.route) {
            Dashboard(navController = navController)
        }
        composable(route = BottomBarScreen.Messages.route) {
            Messages(navController = navController)
        }
        composable(route = BottomBarScreen.Settings.route) {
            Settings(navController = navController)
        }
    }
}