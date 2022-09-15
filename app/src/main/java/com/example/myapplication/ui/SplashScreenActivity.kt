package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.common.initLocation
import com.example.myapplication.common.initUser
import com.example.myapplication.common.utils.ConnectivityStatus
import com.example.myapplication.database.firebase.initFirebase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { true }
        }

        setContent {
            initFirebase()
            initLocation()
            initUser()
            ConnectivityStatus()

            lifecycleScope.launchWhenCreated {
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}