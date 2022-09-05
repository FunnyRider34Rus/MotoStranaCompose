package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.R
import com.example.myapplication.ui.screens.dashboard.Dashboard
import com.example.myapplication.ui.screens.dashboard.DashboardViewModel
import com.example.myapplication.ui.screens.messages.Messages
import com.example.myapplication.ui.screens.registration.InputPhoneNumber
import com.example.myapplication.ui.screens.registration.InputSMSCode
import com.example.myapplication.ui.screens.registration.InputUserInfo
import com.example.myapplication.ui.screens.registration.Welcome
import com.example.myapplication.ui.screens.settings.Settings

@Composable
fun NavigationGraph(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = BottomNavItem.Dashboard.route
    ) {
        composable(route = BottomNavItem.Dashboard.route) {
            val dashboardViewModel = hiltViewModel<DashboardViewModel>()
            Dashboard(navController, dashboardViewModel)
        }
        composable(route = BottomNavItem.Messages.route) {
            Messages(navController)
        }
        composable(route = BottomNavItem.Settings.route) {
            Settings(navController)
        }
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

sealed class BottomNavItem(
    val icon: Int,
    val route: String
) {
    object Dashboard : BottomNavItem(
        icon = R.drawable.ic_menu_dashboard,
        route = "DASHBOARD"
    )

    object Messages : BottomNavItem(
        icon = R.drawable.ic_menu_message,
        route = "MESSAGES"
    )

    object Settings : BottomNavItem(
        icon = R.drawable.ic_menu_settings,
        route = "SETTINGS"
    )
}

sealed class AuthScreen(val route: String) {
    object Welcome : AuthScreen(route = "WELCOME")
    object InputUserPhone : AuthScreen(route = "INPUT_USER_PHONE")
    object InputSMSCode : AuthScreen(route = "INPUT_SMS_CODE")
    object InputUserInfo : AuthScreen(route = "INPUT_USER_INFO")
}