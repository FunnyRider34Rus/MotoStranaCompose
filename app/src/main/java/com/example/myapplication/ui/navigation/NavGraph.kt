package com.example.myapplication.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.MainScreen
import com.example.myapplication.ui.screens.dashboard.Dashboard
import com.example.myapplication.ui.screens.messages.Messages
import com.example.myapplication.ui.screens.registration.InputPhoneNumber
import com.example.myapplication.ui.screens.registration.InputSMSCode
import com.example.myapplication.ui.screens.registration.InputUserInfo
import com.example.myapplication.ui.screens.registration.Welcome
import com.example.myapplication.ui.screens.settings.Settings
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Screen.MainScreen.route
        ) {
            composable(route = Screen.MainScreen.route) {
                MainScreen(navController = navController)
            }
            composable(route = BottomBarScreen.Dashboard.route) {
                Dashboard()
            }
            composable(route = BottomBarScreen.Messages.route) {
                Messages()
            }
            composable(route = BottomBarScreen.Settings.route) {
                Settings()
            }
            composable(route = Screen.Welcome.route) {
                Welcome(navController = navController)
            }
            composable(route = Screen.InputPhoneNumber.route) {
                InputPhoneNumber(navController)
            }
            composable(
                route = Screen.InputSMSCode.route + "/{phone}",
                arguments = listOf(
                    navArgument("phone") {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = false
                    }
                )) { entry ->
                InputSMSCode(
                    navController,
                    entry.arguments?.getString("phone").toString()
                )
            }
            composable(
                route = Screen.InputUserInfo.route + "/{phone}",
                arguments = listOf(
                    navArgument("phone") {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = false
                    }
                )) { entry ->
                InputUserInfo(
                    navController,
                    entry.arguments?.getString("phone").toString()
                )
            }
        }
}