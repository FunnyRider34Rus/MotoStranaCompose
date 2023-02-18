package com.example.motostranacompose.core

sealed class Screen(val route: String) {
    object auth : Screen("auth")
}
