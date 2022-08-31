package com.example.myapplication.ui.screens.dashboard.model

sealed class DashboardEvent {
    object NewsClicked : DashboardEvent()
    object EventClicked : DashboardEvent()
    data class ItemNewsClicked (val index: Int) : DashboardEvent()
    data class ItemEventClicked (val index: Int): DashboardEvent()
}
