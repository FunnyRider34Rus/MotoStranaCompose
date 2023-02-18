package com.example.motostranacompose

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //инициализируем Firebase
        FirebaseApp.initializeApp(this)
        //включаем кэширование
        Firebase.database.setPersistenceEnabled(true)
    }
}