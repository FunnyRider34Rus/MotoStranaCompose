package com.example.myapplication.common

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.App.Companion.cityName
import com.example.myapplication.App.Companion.stateName
import com.example.myapplication.common.utils.LocationViewModel

@Composable
fun initLocation() {
    val context = LocalContext.current
    var result = true
    val fineLocation =
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
    val requestSinglePermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            result = isGranted
        }

    if (fineLocation == PERMISSION_GRANTED) {
        requestLocationUpdates()
    } else {
        SideEffect {
            requestSinglePermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    if (result) {
        requestLocationUpdates()
    } else {
        ShowError(error = Error.NO_LOCATION)
    }
}

@Composable
fun requestLocationUpdates(locationViewModel: LocationViewModel = viewModel()) {
    locationViewModel.startLocationUpdates()
    val location = locationViewModel.getLocationLiveData().observeAsState()
    if (location.value?.city != null && location.value?.state != null) {
        cityName = rememberSaveable { mutableStateOf(location.value!!.city) }
        stateName = rememberSaveable { mutableStateOf(location.value!!.state) }
    }
}
