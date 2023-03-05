package com.example.motostranacompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.motostranacompose.ui.authentication.ScreenAuth
import com.example.motostranacompose.ui.chat.ScreenChat
import com.example.motostranacompose.ui.dashboard.detail.ScreenDashboardDetail
import com.example.motostranacompose.ui.dashboard.list.ScreenDashboardList
import com.example.motostranacompose.ui.profile.ScreenProfile
import com.example.motostranacompose.ui.ride.ScreenRide

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        route = Graph.NAVGRAPH.route
    ) {
        composable(route = Screen.DASHLIST.route) {
            ScreenDashboardList(navController = navController, modifier = modifier)
        }
        composable(route = Screen.CHAT.route) {
            ScreenChat(navController = navController, modifier = modifier)
        }
        composable(route = Screen.RIDE.route) {
            ScreenRide(navController = navController, modifier = modifier)
        }
        composable(route = Screen.PROFILE.route) {
            ScreenProfile(navController = navController, modifier = modifier)
        }
        composable(route = Screen.AUTH.route) {
            ScreenAuth(navController = navController, authViewModel = hiltViewModel())
        }
        composable(route = Screen.DASHDETAIL.route + "/{content}") { navEntry ->
            val id = navEntry.arguments?.getString("content")
            id?.let { ScreenDashboardDetail(navController = navController, id = it) }
        }
    }
}

sealed class Screen(val route: String) {
    object AUTH : Screen(route = "auth")
    object DASHLIST : Screen(route = "dashboard_list")
    object DASHDETAIL : Screen(route = "dashboard_detail")
    object CHAT : Screen(route = "chat")
    object RIDE : Screen(route = "ride")
    object PROFILE : Screen(route = "profile")
}

sealed class Graph(val route: String) {
    object NAVGRAPH : Graph("root_nav_graph")
}