package com.example.motostranacompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.motostranacompose.ui.authentication.AuthScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.AuthScreen.route) {
            AuthScreen(navController = navController)
        }
        composable(route = Screen.DashboardListScreen.route) {

        }
        composable(route = Screen.DashboardDetailScreen.route) {

        }
    }
}