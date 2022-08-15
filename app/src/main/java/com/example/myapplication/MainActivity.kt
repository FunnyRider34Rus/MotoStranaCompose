package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.database.initFirebase
import com.example.myapplication.ui.navigation.SetupNavGraph
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.pager.ExperimentalPagerApi

lateinit var ACTIVITY_CONTEXT: ComponentActivity

@ExperimentalPagerApi
@ExperimentalMaterialApi
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

@ExperimentalPagerApi
@ExperimentalMaterialApi
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
