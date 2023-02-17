@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.motostranacompose.ui.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.motostranacompose.R
import com.example.motostranacompose.ui.theme.MotoStranaComposeTheme

@Composable
fun AuthScreen(navController: NavController, authViewModel: AuthViewModel) {

    val viewState = authViewModel.viewState.collectAsState(AuthViewState())
    val scrollState = rememberScrollState()
    with(viewState.value) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.auth_title),
                            maxLines = 1
                        )
                    }
                )
            }, content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.auth_body),
                        modifier = Modifier.verticalScroll(scrollState)
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
                                onCheckedChange = { authViewModel.obtainEvent(AuthEvent.CheckBoxClick) })
                            Text(text = stringResource(id = R.string.auth_checkbox_text))
                        }
                        Button(
                            onClick = { authViewModel.obtainEvent(AuthEvent.AuthButtonClick) },
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
        val authViewModel: AuthViewModel = viewModel()
        AuthScreen(navController, authViewModel)
    }
}