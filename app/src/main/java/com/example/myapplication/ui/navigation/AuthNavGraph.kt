package com.example.myapplication.ui.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
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
            Welcome(navController = navController)
        }
        composable(route = AuthScreen.InputUserPhone.route) {
            InputPhoneNumber(navController = navController)
        }
        composable(route = AuthScreen.InputSMSCode.route + "/{phone}",
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
        composable(route = AuthScreen.InputUserInfo.route + "/{phone}",
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
    object InputUserPhone : AuthScreen(route = "INPUT_USER_PHONE")
    object InputSMSCode : AuthScreen(route = "INPUT_SMS_CODE")
    object InputUserInfo : AuthScreen(route = "INPUT_USER_INFO")
}