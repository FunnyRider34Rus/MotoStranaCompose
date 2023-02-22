package com.example.motostranacompose.ui.authentication

import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.motostranacompose.R
import com.example.motostranacompose.ui.theme.MotoStranaComposeTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val viewState = viewModel.viewState.collectAsState(AuthViewState())
    val appBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val textScrollState = rememberScrollState()

    with(viewState.value) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.auth_title),
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                )
            }, content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                ) {
                    Text(
                        text = stringResource(id = R.string.auth_body),
                        modifier = Modifier
                            .verticalScroll(textScrollState)
                            .nestedScroll(appBarScrollBehavior.nestedScrollConnection),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Column(
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Row(modifier = Modifier.padding(vertical = 16.dp)) {
                            Checkbox(
                                checked = isCheck,
                                onCheckedChange = { viewModel.obtainEvent(AuthEvent.CheckBoxClick) })
                            Text(
                                text = stringResource(id = R.string.auth_checkbox_text),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Button(
                            onClick = {

                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            enabled = isCheck,
                            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_google_logo),
                                contentDescription = "SignIn with Google",
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                            Text(text = stringResource(id = R.string.auth_button))
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AuthPreview() {
    MotoStranaComposeTheme {
        val navController = rememberNavController()
        val authViewModel: AuthViewModel = hiltViewModel()
        AuthScreen(navController, authViewModel)
    }
}