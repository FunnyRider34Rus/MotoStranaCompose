package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.database.AUTH
import com.example.myapplication.database.initFirebase
import com.example.myapplication.ui.screens.registration.navigation.Screen
import com.example.myapplication.ui.screens.registration.navigation.SetupNavGraph
import com.example.myapplication.ui.theme.MyApplicationTheme

lateinit var ACTIVITY_CONTEXT: ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ACTIVITY_CONTEXT = this
            initFirebase()
            App()
        }
    }
}

@Composable
fun App() {

    lateinit var navController: NavHostController

    MyApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            navController = rememberNavController()
            SetupNavGraph(navController = navController)
        }
    }
}
