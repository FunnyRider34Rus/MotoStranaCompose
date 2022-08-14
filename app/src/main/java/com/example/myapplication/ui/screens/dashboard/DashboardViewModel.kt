package com.example.myapplication.ui.screens.dashboard

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Event

class DashboardViewModel : ViewModel() {

    val events : List<Event> = mutableStateListOf()

    fun getEvent(event: String) : List<Event> {
        when(event) {
            "news" -> {
                //TODO get news
            }
            "event" -> {
                //TODO get events
            }
        }
        return events
    }
}