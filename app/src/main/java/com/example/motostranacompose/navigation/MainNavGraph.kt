package com.example.motostranacompose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.motostranacompose.ui.chat.ScreenChat
import com.example.motostranacompose.ui.dashboard.detail.ScreenDashboardDetail
import com.example.motostranacompose.ui.dashboard.list.ScreenDashboardList
import com.example.motostranacompose.ui.profile.ScreenProfile
import com.example.motostranacompose.ui.ride.ScreenRide

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation(startDestination = Screen.DASHLIST.route, route = Screen.MAIN.route){
        composable(route = Screen.DASHLIST.route) {
            ScreenDashboardList(navController = navController)
        }
        composable(route = Screen.DASHDETAIL.route) {
            ScreenDashboardDetail(navController = navController)
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