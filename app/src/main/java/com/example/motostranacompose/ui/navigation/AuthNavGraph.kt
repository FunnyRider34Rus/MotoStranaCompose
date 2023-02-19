package com.example.motostranacompose.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.motostranacompose.ui.authentication.AuthScreen
import com.example.motostranacompose.ui.authentication.AuthViewModel

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = Screen.auth.route
    ) {
        composable(route = Screen.auth.route) {
            val authViewModel: AuthViewModel = hiltViewModel()
            AuthScreen(navController, authViewModel)
        }
    }
}