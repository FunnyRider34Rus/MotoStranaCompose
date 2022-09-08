package com.example.myapplication.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun initLocation(locationViewModel: LocationViewModel = viewModel()) {
    val location = locationViewModel.getLocationLiveData().observeAsState()
    val cityName by rememberSaveable { mutableStateOf(location.value?.city) }
    val stateName by rememberSaveable { mutableStateOf(location.value?.state) }
}