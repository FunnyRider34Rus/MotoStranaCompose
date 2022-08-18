package com.example.myapplication.ui.screens

import com.example.myapplication.ACTIVITY_CONTEXT
import com.example.myapplication.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Dashboard : BottomBarScreen(
        route = "DASHBOARD",
        title = ACTIVITY_CONTEXT.getString(R.string.dashboard),
        icon = R.drawable.ic_menu_dashboard
    )

    object Messages : BottomBarScreen(
        route = "MESSAGES",
        title = ACTIVITY_CONTEXT.getString(R.string.messages),
        icon = R.drawable.ic_menu_message
    )

    object Settings : BottomBarScreen(
        route = "SETTINGS",
        title = ACTIVITY_CONTEXT.getString(R.string.settings),
        icon = R.drawable.ic_menu_settings
    )
}
