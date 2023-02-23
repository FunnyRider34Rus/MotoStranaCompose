package com.example.motostranacompose.ui.authentication

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.motostranacompose.core.Utils.Companion.printToLog
import com.example.motostranacompose.ui.authentication.components.AuthContent
import com.example.motostranacompose.ui.authentication.components.AuthTopBar
import com.example.motostranacompose.ui.authentication.components.OneTapSignIn
import com.example.motostranacompose.ui.authentication.components.SignInWithGoogle
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit
) {
    val viewState = viewModel.viewState.collectAsState(AuthViewState())
    val appBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val textScrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        topBar = {
            AuthTopBar()
        },
        content = { padding ->
            AuthContent(
                padding = padding,
                appBarScrollBehavior = appBarScrollBehavior,
                textScrollState = textScrollState,
                isCheck = viewState.value.isCheck,
                onCheckBoxClicked = {
                    viewModel.obtainEvent(AuthEvent.CheckBoxClick)
                },
                onAuthButtonClicked = {
                    viewModel.obtainEvent(AuthEvent.AuthButtonClick)
                }
            )
        }
    )

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                val credentials = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                viewModel.signInWithGoogle(googleCredentials)
            } catch (it: ApiException) {
                printToLog(it)
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

    SignInWithGoogle(
        navigateToHomeScreen = { signedIn ->
            if (signedIn) {
                navigateToHomeScreen()
            }
        }
    )
}