package com.example.motostranacompose.ui.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun ScreenChat(navController: NavController, modifier: Modifier) {
    Text(
        modifier = modifier
            .fillMaxSize(),
        textAlign = TextAlign.Center,
        text = "Chat"
    )
}