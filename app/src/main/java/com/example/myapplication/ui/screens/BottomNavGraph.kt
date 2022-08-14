package com.example.myapplication.ui.screens

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.dashboard.Dashboard
import com.example.myapplication.ui.screens.messages.Messages
import com.example.myapplication.ui.screens.settings.Settings

@Composable
fun BottomNavGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = BottomBarScreen.Dashboard.route
        ) {
            composable(route = BottomBarScreen.Dashboard.route) {
                Dashboard()
            }
            composable(route = BottomBarScreen.Messages.route) {
                Messages()
            }
            composable(route = BottomBarScreen.Settings.route) {
                Settings()
            }
        }
}