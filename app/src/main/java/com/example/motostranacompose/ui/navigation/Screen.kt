package com.example.motostranacompose.ui.navigation

import com.example.motostranacompose.R

sealed class Screen(val route: String) {
    object auth : Screen("auth")

    object dashboard_detail: Screen("dash_detail")
}

sealed class BottomBarScreen(val title:String, val icon:Int, val route:String) {
    object dashboard_list: BottomBarScreen("Лента", R.drawable.ic_dashboard,"dash_list")
    object messages: BottomBarScreen("Чат", R.drawable.ic_message,"messages")
    object rideMode: BottomBarScreen("Драйв", R.drawable.ic_ride,"rideMode")
    object profile: BottomBarScreen("Профиль", R.drawable.ic_settings,"profile")
}
