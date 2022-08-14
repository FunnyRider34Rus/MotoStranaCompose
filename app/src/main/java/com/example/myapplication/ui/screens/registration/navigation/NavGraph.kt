package com.example.myapplication.ui.screens.registration.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.MainScreen
import com.example.myapplication.ui.screens.registration.*

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.Welcome.route) {
            Welcome(navController)
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
        composable(route = Screen.MainScreen.route) {
            MainScreen()
        }
    }
}