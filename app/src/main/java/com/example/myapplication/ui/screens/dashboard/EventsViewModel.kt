package com.example.myapplication.ui.screens.dashboard


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.REMOTE_DATABASE
import com.example.myapplication.models.Event

class EventsViewModel : ViewModel() {
    private val _events = mutableStateListOf<Event>()

    val events: List<Event>
        get() = _events

    fun getEvents(event: String) {
        REMOTE_DATABASE.child(event).orderByChild("date").get()
            .addOnSuccessListener { documentSnapshot ->
                documentSnapshot.getValue(Event::class.java)?.let { _events.add(it) }
            }
    }
}