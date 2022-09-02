package com.example.myapplication.ui.screens.dashboard.model

sealed class DashboardEvent {
    object NewsClicked : DashboardEvent()
    object EventClicked : DashboardEvent()
    data class ItemClicked (val index: Int) : DashboardEvent()
}
