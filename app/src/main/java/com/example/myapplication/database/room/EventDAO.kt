package com.example.myapplication.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.models.Event

@Dao
interface EventDAO {
    //@Query("SELECT * from events")
    //fun getAllEvents(event: String) : LiveData<List<Event>>

    //@Insert
    //suspend fun insert(item: Event)

    //@Delete
    //suspend fun delete(item: Event)
}