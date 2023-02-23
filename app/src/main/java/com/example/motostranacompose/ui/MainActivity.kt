package com.example.motostranacompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.motostranacompose.navigation.NavGraph
import com.example.motostranacompose.navigation.Screen
import com.example.motostranacompose.ui.theme.MotoStranaComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotoStranaComposeTheme {
                CheckAuthState()
            }
        }
    }

    @Composable
    private fun CheckAuthState() {
        navController = rememberNavController()
        val startDestination = if(viewModel.isUserAuthenticated) {
            Screen.DashboardListScreen.route
        } else {
            Screen.AuthScreen.route
        }
        NavGraph(navController = navController, startDestination = startDestination)
    }
}