package com.example.motostranacompose.ui.dashboard.detail

interface DashboardDetailEvent {
    class getContentByKey(val key: String?) : DashboardDetailEvent
}