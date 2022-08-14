package com.example.myapplication.ui.screens.dashboard.tabs.navigation

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(
    var title: String,
    var screen: ComposableFun
) {
    object News : TabItem("Новости", { News })
    object Events : TabItem("События", { Events })
}
