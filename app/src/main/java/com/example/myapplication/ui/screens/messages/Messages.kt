package com.example.myapplication.ui.screens.messages

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapplication.ui.screens.BottomNavigationMenu

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Messages(navController: NavController){
    Scaffold(
        bottomBar = { BottomNavigationMenu(navController = navController) }
    ) {  paddingValues ->

    }
}