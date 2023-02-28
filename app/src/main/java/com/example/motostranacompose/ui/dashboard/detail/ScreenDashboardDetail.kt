package com.example.motostranacompose.ui.dashboard.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun ScreenDashboardDetail(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center,
            text = "DashboardDetail"
        )
    }
}