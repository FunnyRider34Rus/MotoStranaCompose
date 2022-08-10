package com.example.myapplication.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.registration.InputPhoneNumber
import com.example.myapplication.ui.screens.registration.InputSMSCode
import com.example.myapplication.ui.screens.registration.Welcome

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Navigate.Welcome.route) {
        composable(route = Navigate.Welcome.route) {
            Welcome(navController)
        }
        composable(route = Navigate.InputPhoneNumber.route) {
            InputPhoneNumber(navController)
        }
        composable(
            route = Navigate.InputSMSCode.route + "/{phone}",
            arguments = listOf(
                navArgument("phone") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )) { entry ->
            InputSMSCode(
                navController,
                entry.arguments?.getString("phone").toString())
        }
    }
}