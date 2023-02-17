package com.example.motostranacompose.core

sealed class Screens(val route: String) {
    object auth : Screens("auth")
}
