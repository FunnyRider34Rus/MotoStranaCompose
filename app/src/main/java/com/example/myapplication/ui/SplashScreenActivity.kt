package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.common.initLocation
import com.example.myapplication.common.initUser
import com.example.myapplication.common.utils.ConnectivityStatus
import com.example.myapplication.database.firebase.initFirebase
import com.example.myapplication.ui.navigation.NavigationGraph
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.white
import kotlinx.coroutines.ExperimentalCoroutinesApi

lateinit var ACTIVITY_CONTEXT: ComponentActivity
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { true }
        }

        setContent {
            ACTIVITY_CONTEXT = this

            initFirebase()
            initLocation()
            initUser()
            ConnectivityStatus()

            MyApplicationTheme {
                Surface(color = white) {
                    val navController = rememberNavController()
                    NavigationGraph(navController = navController)
                }
            }
        }
    }
}