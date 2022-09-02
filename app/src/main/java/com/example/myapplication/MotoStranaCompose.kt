package com.example.myapplication

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MotoStranaCompose : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: MotoStranaCompose? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}