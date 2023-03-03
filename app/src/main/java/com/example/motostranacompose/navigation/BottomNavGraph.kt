package com.example.motostranacompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.motostranacompose.ui.chat.ScreenChat
import com.example.motostranacompose.ui.dashboard.list.ScreenDashboardList
import com.example.motostranacompose.ui.profile.ScreenProfile
import com.example.motostranacompose.ui.ride.ScreenRide

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.DASHLIST.route,
        route = Graph.BOTTOMNAVGRAPH.route,
    ){
        composable(route = Screen.DASHLIST.route) {
            ScreenDashboardList(navController = navController, listViewModel = hiltViewModel())
        }
        composable(route = Screen.CHAT.route) {
            ScreenChat(navController = navController)
        }
        composable(route = Screen.RIDE.route) {
            ScreenRide(navController = navController)
        }
        composable(route = Screen.PROFILE.route) {
            ScreenProfile(navController = navController)
        }
    }
}