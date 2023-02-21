package com.example.motostranacompose.core

import android.util.Log
import com.example.motostranacompose.core.Constants.TAG

class Utils {
    companion object {
        fun printToLog(e: Exception) {
            Log.d(TAG, e.message ?: e.toString())
        }
    }
}