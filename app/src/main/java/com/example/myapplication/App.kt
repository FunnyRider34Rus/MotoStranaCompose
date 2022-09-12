package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.compose.runtime.MutableState
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    init {
        instance = this
    }

    companion object {
        var instance: App? = null
        lateinit var cityName: MutableState<String>
        lateinit var stateName: MutableState<String>


        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}