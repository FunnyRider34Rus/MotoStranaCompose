package com.example.motostranacompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.motostranacompose.ui.authentication.AuthScreen
import com.example.motostranacompose.ui.dashboard.detail.DashboardDetailScreen
import com.example.motostranacompose.ui.dashboard.list.DashboardListScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.AuthScreen.route) {
            AuthScreen(navigateToHomeScreen = {
                navController.navigate(Screen.DashboardListScreen.route)
            })
        }
        composable(route = Screen.DashboardListScreen.route) {
            DashboardListScreen(navController = navController)
        }
        composable(route = Screen.DashboardDetailScreen.route) {
            DashboardDetailScreen(navController = navController)
        }
    }
}