package com.example.motostranacompose.ui.authentication

data class AuthViewState(
    val isCheck: Boolean = false,
    val isClick: Boolean = false,
    val isUserAuth: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)