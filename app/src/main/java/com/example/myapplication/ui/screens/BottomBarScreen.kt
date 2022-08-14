package com.example.myapplication.ui.screens

import com.example.myapplication.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Dashboard : BottomBarScreen(
        route = "dashboard",
        title = "События",
        icon = R.drawable.ic_menu_dashboard
    )
    object Messages : BottomBarScreen(
        route = "messages",
        title = "Сообщения",
        icon = R.drawable.ic_menu_message
    )
    object Settings : BottomBarScreen(
        route = "settings",
        title = "Настройки",
        icon = R.drawable.ic_menu_settings
    )
}
