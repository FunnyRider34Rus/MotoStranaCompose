package com.example.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.registration.InputPhoneNumber
import com.example.myapplication.ui.screens.registration.InputSMSCode
import com.example.myapplication.ui.screens.registration.Welcome

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            Welcome(navController)
        }
        composable("input_phone_number") {
            InputPhoneNumber(navController)
        }
        composable("input_sms_code") {
            InputSMSCode(navController)
        }
    }
}