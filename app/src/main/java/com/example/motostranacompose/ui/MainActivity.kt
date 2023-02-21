package com.example.motostranacompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
                navController = rememberNavController()
                NavGraph(navController = navController, startDestination = Screen.AuthScreen.route)
            }
        }
    }
}