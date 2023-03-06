package com.example.motostranacompose.ui.authentication

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.motostranacompose.core.Constants.TAG
import com.example.motostranacompose.core.components.ProgressBar
import com.example.motostranacompose.ui.authentication.components.AuthScreen
import com.example.motostranacompose.ui.authentication.components.OneTapSignIn
import com.example.motostranacompose.ui.authentication.components.SignInWithGoogle
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun ScreenAuth(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {

    val viewState = authViewModel.viewState.collectAsState(AuthViewState())
    if (viewState.value.isLoading) ProgressBar()

    AuthScreen(
        isChecked = viewState.value.isCheck,
        onCheckedChange = { authViewModel.obtainEvent(AuthEvent.CheckBoxClick) },
        onClick = { authViewModel.obtainEvent(AuthEvent.ButtonClick) }
    )

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    val credentials =
                        authViewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                    val googleIdToken = credentials.googleIdToken
                    val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                    authViewModel.signInWithGoogle(googleCredentials)
                } catch (e: ApiException) {
                    Log.e(TAG, e.message ?: e.toString())
                }
            }
        }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    OneTapSignIn(
        launch = {
            launch(it)
        }
    )
    SignInWithGoogle(navController = navController)
}