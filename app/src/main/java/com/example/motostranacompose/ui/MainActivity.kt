package com.example.motostranacompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.motostranacompose.navigation.NavGraph
import com.example.motostranacompose.navigation.Screen
import com.example.motostranacompose.ui.theme.MotoStranaComposeTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotoStranaComposeTheme {
                CheckAuthUser()
            }
        }
    }

    @Composable
    private fun CheckAuthUser() {
        val startDestination = if (FirebaseAuth.getInstance().currentUser?.uid != null) {
            Screen.AUTH.route
        } else {
            Screen.MAIN.route
        }
        NavGraph(
            navController = rememberNavController(),
            startDestination = startDestination
        )
    }
}