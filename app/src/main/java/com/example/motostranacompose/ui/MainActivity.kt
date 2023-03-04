package com.example.motostranacompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.motostranacompose.navigation.Screen
import com.example.motostranacompose.ui.dashboard.list.ScreenContainer
import com.example.motostranacompose.ui.theme.MotoStranaComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

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
        //FirebaseAuth.getInstance().signOut()
        val startDestination = if (viewModel.isUserAuthenticated) {
            Screen.DASHLIST.route
        } else {
            Screen.AUTH.route
        }
        ScreenContainer(
            navController = rememberNavController(),
            startDestination = startDestination
        )
    }
}