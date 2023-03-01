package com.example.motostranacompose.ui

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.motostranacompose.core.components.BottomBar
import com.example.motostranacompose.navigation.BottomNavGraph

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun ScreenMain() {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {  _ ->
        BottomNavGraph(navController = navController)
    }
}