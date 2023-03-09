package com.example.motostranacompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.motostranacompose.R
import com.example.motostranacompose.core.InternetConnectionState
import com.example.motostranacompose.core.components.ShowError
import com.example.motostranacompose.core.components.connectivityState
import com.example.motostranacompose.navigation.Screen
import com.example.motostranacompose.ui.container.ScreenContainer
import com.example.motostranacompose.ui.theme.MotoStranaComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotoStranaComposeTheme {
                CheckInternetConnection()
                CheckAuthUser()
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Composable
    private fun CheckInternetConnection() {
        val connection by connectivityState()
        val isConnected = connection === InternetConnectionState.Available
        if (!isConnected) {
            ShowError(text = stringResource(id = R.string.text_internet_connection_error))
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