package com.example.myapplication.database.room

import androidx.lifecycle.LiveData
import com.example.myapplication.models.Event

class EventRepository(private val eventDatabaseDAO : EventDAO, event: String) {

    //val readAllEvents : LiveData<List<Event>> = eventDatabaseDAO.getAllEvents(event)
}