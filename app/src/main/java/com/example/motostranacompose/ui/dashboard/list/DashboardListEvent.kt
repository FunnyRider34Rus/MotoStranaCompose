package com.example.motostranacompose.ui.dashboard.list

sealed class DashboardListEvent {
    object contentClick : DashboardListEvent()
    object  likeClick : DashboardListEvent()
    object commentClick : DashboardListEvent()
    object shareClick : DashboardListEvent()
    object addButtonClick : DashboardListEvent()
}
