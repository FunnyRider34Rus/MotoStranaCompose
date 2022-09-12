package com.example.myapplication

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    init {
        instance = this
    }

    companion object {
        var instance: App? = null
        lateinit var cityName: String
        lateinit var stateName: String


        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}