package com.example.motostranacompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.motostranacompose.ui.ScreenMain
import com.example.motostranacompose.ui.authentication.ScreenAuth
import com.example.motostranacompose.ui.dashboard.detail.ScreenDashboardDetail

@Composable
fun NavGraph(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        route = Graph.NAVGRAPH.route
    ) {
        bottomNavGraph(navController = navController)
        composable(route = Screen.AUTH.route) {
            ScreenAuth(navController = navController, authViewModel = hiltViewModel())
        }
        composable(route = Screen.MAIN.route) {
            ScreenMain(navController = navController)
        }
        composable(route = Screen.DASHDETAIL.route + "/{content}") { navEntry ->
            val id = navEntry.arguments?.getString("content")
            id?.let { id -> ScreenDashboardDetail(navController = navController, listViewModel = hiltViewModel(), id = id) }
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

sealed class Graph(val route: String) {
    object NAVGRAPH : Graph("root_nav_graph")
    object BOTTOMNAVGRAPH : Graph("bottom_nav_graph")
}