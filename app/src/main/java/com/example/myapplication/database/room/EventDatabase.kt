package com.example.myapplication.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.MotoStranaCompose
import com.example.myapplication.models.Event

@Database(entities = [Event::class], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDAO() : EventDAO

    companion object {
        private var INSTANCE : EventDatabase? = null

        fun getInstance() : EventDatabase {
            synchronized(this) {
                var instance = INSTANCE
                val context: Context = MotoStranaCompose.applicationContext()

                if (instance == null) {
                    instance = Room.databaseBuilder(context, EventDatabase::class.java, "event_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            return instance
            }
        }
    }
}