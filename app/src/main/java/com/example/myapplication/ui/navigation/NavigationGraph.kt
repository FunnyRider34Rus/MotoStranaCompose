package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.R
import com.example.myapplication.ui.screens.dashboard.Dashboard
import com.example.myapplication.ui.screens.dashboard.DashboardViewModel
import com.example.myapplication.ui.screens.messages.Messages
import com.example.myapplication.ui.screens.settings.Settings

@Composable
fun NavigationGraph(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = BottomNavItem.Dashboard.route
    ) {
        composable(route = BottomNavItem.Dashboard.route) {
            val dashboardViewModel = hiltViewModel<DashboardViewModel>()
            Dashboard(navController, dashboardViewModel)
        }
        composable(route = BottomNavItem.Messages.route) {
            Messages(navController)
        }
        composable(route = BottomNavItem.Settings.route) {
            Settings(navController)
        }
    }
}

sealed class BottomNavItem(
    val icon: Int,
    val route: String
) {
    object Dashboard : BottomNavItem(
        icon = R.drawable.ic_menu_dashboard,
        route = "DASHBOARD"
    )

    object Messages : BottomNavItem(
        icon = R.drawable.ic_menu_message,
        route = "MESSAGES"
    )

    object Settings : BottomNavItem(
        icon = R.drawable.ic_menu_settings,
        route = "SETTINGS"
    )
}