package com.example.myapplication.ui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapplication.ui.screens.BottomNavigationMenu

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Settings(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationMenu(navController = navController) }
    ) {  paddingValues ->

    }
}