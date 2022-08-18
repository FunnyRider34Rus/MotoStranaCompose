package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.database.initFirebase
import com.example.myapplication.ui.navigation.RootNavigationGraph
import com.example.myapplication.ui.theme.MotoStranaTheme

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
    MotoStranaTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            RootNavigationGraph(navController = rememberNavController())
        }
    }
}
