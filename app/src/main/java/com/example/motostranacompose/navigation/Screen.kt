package com.example.motostranacompose.navigation

sealed class Screen (val route: String) {
    object AuthScreen : Screen("auth")
    object DashboardListScreen : Screen ("dashboard_list")
    object DashboardDetailScreen : Screen("dashboard_detail")
}
