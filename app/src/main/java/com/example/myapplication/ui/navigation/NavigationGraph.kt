package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.R
import com.example.myapplication.ui.screens.dashboard.Dashboard
import com.example.myapplication.ui.screens.dashboard.DashboardViewModel
import com.example.myapplication.ui.screens.messages.Messages
import com.example.myapplication.ui.screens.settings.Settings

@Composable
fun NavigationGraph(navController: NavController) {

    val dashboardViewModel = hiltViewModel<DashboardViewModel>()

    NavHost(
        navController = navController as NavHostController,
        startDestination = BottomNavItem.Dashboard.route
    ) {
        composable(route = BottomNavItem.Dashboard.route) {
            Dashboard(dashboardViewModel)
        }
        composable(route = BottomNavItem.Messages.route) {
            Messages()
        }
        composable(route = BottomNavItem.Settings.route) {
            Settings()
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