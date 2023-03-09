package com.example.motostranacompose.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.google.firebase.Timestamp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.text.SimpleDateFormat
import java.util.*

fun timestampToDate(timestamp: Timestamp?): String {
    val long = (timestamp?.seconds?.times(1000) ?: 0) + (timestamp?.nanoseconds?.div(1000000) ?: 0)
    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return simpleDateFormat.format(long)
}

fun addOrRemoveIfContains(source: List<String>?, value: String): List<String>? {
    var modified = source?.toMutableList()
    if (source != null) {
        if (source.contains(value)) {
            modified?.remove(value)
        } else {
            modified?.add(value)
        }
    } else {
        modified = mutableListOf(value)
    }
    return modified
}

sealed class InternetConnectionState {
    object Available : InternetConnectionState()
    object Unavailable : InternetConnectionState()
}

@ExperimentalCoroutinesApi
fun Context.observeConnectivityAsFlow() = callbackFlow {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val callback = NetworkCallback { connectionState -> trySend(connectionState) }

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    connectivityManager.registerNetworkCallback(networkRequest, callback)

    // Set current state
    val currentState = getCurrentConnectivityState(connectivityManager)
    trySend(currentState)

    // Remove callback when not used
    awaitClose {
        // Remove listeners
        connectivityManager.unregisterNetworkCallback(callback)
    }
}

fun NetworkCallback(callback: (InternetConnectionState) -> Unit): ConnectivityManager.NetworkCallback {
    return object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            callback(InternetConnectionState.Available)
        }

        override fun onLost(network: Network) {
            callback(InternetConnectionState.Unavailable)
        }
    }
}

private fun getCurrentConnectivityState(
    connectivityManager: ConnectivityManager
): InternetConnectionState {
    val connected = connectivityManager.allNetworks.any { network ->
        connectivityManager.getNetworkCapabilities(network)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }

    return if (connected) InternetConnectionState.Available else InternetConnectionState.Unavailable
}

val Context.currentConnectivityState: InternetConnectionState
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return getCurrentConnectivityState(connectivityManager)
    }