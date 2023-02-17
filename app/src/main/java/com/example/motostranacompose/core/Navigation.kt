package com.example.motostranacompose.core

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.motostranacompose.ui.authentication.AuthScreen
import com.example.motostranacompose.ui.authentication.AuthViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    NavHost(navController = navController, startDestination = Screens.auth.route) {
        composable(route = Screens.auth.route) {
            AuthScreen(navController, authViewModel)
        }
    }
}