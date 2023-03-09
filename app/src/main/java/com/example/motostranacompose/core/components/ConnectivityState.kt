package com.example.motostranacompose.core.components

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import com.example.motostranacompose.core.InternetConnectionState
import com.example.motostranacompose.core.currentConnectivityState
import com.example.motostranacompose.core.observeConnectivityAsFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun connectivityState(): State<InternetConnectionState> {
    val context = LocalContext.current

    // Creates a State<ConnectionState> with current connectivity state as initial value
    return produceState(initialValue = context.currentConnectivityState) {
        // In a coroutine, can make suspend calls
        context.observeConnectivityAsFlow().collect { value = it }
    }
}