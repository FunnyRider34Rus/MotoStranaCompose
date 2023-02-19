package com.example.motostranacompose.ui.dashboard.list

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DashboardListScreen(navController: NavController) {
    Surface {
        Text(text = "Dashboard")
    }
}