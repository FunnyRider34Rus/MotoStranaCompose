package com.example.motostranacompose.ui.ride

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.motostranacompose.core.components.BottomBar

@Composable
fun ScreenRide(navController: NavController) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { paddingValue ->
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
            textAlign = TextAlign.Center,
            text = "RideMode"
        )
    }
}