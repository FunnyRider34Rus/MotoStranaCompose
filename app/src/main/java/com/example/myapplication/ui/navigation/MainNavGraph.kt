package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ACTIVITY_CONTEXT
import com.example.myapplication.R
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
            Dashboard()
        }
        composable(route = BottomBarScreen.Profile.route) {
            Messages()
        }
        composable(route = BottomBarScreen.Settings.route) {
            Settings()
        }
        authNavGraph(navController = navController)
    }
}

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Dashboard : BottomBarScreen(
        route = "DASHBOARD",
        title = ACTIVITY_CONTEXT.getString(R.string.dashboard),
        icon = R.drawable.ic_menu_dashboard
    )

    object Profile : BottomBarScreen(
        route = "MESSAGE",
        title = ACTIVITY_CONTEXT.getString(R.string.messages),
        icon = R.drawable.ic_menu_message
    )

    object Settings : BottomBarScreen(
        route = "SETTINGS",
        title = ACTIVITY_CONTEXT.getString(R.string.settings),
        icon = R.drawable.ic_menu_settings
    )
}