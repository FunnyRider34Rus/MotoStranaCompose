package com.example.myapplication.common.utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    private val locationLiveData = LocationLiveData(application)
    fun getLocationLiveData() = locationLiveData
    fun startLocationUpdates() {
        locationLiveData.startLocationUpdates()
    }
}