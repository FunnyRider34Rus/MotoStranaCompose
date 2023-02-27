package com.example.motostranacompose.ui.dashboard.list

sealed class DashboardListEvent {
    object ClickLike : DashboardListEvent()
    object ClickComment : DashboardListEvent()
    object ClickShare : DashboardListEvent()
}