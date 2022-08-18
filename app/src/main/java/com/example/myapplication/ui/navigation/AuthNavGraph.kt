package com.example.myapplication.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.registration.InputPhoneNumber
import com.example.myapplication.ui.screens.registration.InputSMSCode
import com.example.myapplication.ui.screens.registration.InputUserInfo
import com.example.myapplication.ui.screens.registration.Welcome

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Welcome.route
    ) {
        composable(route = AuthScreen.Welcome.route) {
            Welcome(navController)
        }
        composable(route = AuthScreen.InputPhone.route) {
            InputPhoneNumber(navController)
        }
        composable(
            route = AuthScreen.InputSMS.route + "/{phone}",
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
            route = AuthScreen.InputInfo.route + "/{phone}",
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

sealed class AuthScreen(val route: String) {
    object Welcome : AuthScreen(route = "WELCOME")
    object InputPhone : AuthScreen(route = "INPUT_PHONE_NUMBER")
    object InputSMS : AuthScreen(route = "INPUT_SMS_CODE")
    object InputInfo: AuthScreen(route = "INPUT_USER_INFO")
}