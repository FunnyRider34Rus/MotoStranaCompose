package com.example.motostranacompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.motostranacompose.ui.authentication.ScreenAuth

@Composable
fun NavGraph(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        mainNavGraph(navController)
        composable(route = Screen.AUTH.route) {
            ScreenAuth(navController = navController)
        }
    }
}

sealed class Screen(val route: String) {
    object AUTH : Screen(route = "auth")
    object MAIN : Screen(route = "main")
    object DASHLIST : Screen(route = "dashboard_list")
    object DASHDETAIL : Screen(route = "dashboard_detail")
    object CHAT : Screen(route = "chat")
    object RIDE : Screen(route = "ride")
    object PROFILE : Screen(route = "profile")
}