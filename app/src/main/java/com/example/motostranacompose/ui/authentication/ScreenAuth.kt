package com.example.motostranacompose.ui.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.motostranacompose.R

@Composable
fun ScreenAuth(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {

    val viewState = authViewModel.viewState.collectAsState(AuthViewState())
    val textScrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.auth_title)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.auth_body),
                modifier = Modifier
                    .verticalScroll(textScrollState)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = viewState.value.isCheck,
                    onCheckedChange = { authViewModel.obtainEvent(AuthEvent.CheckBoxClick) }
                )
                Text(
                    text = stringResource(id = R.string.auth_checkbox_text)
                )
            }
            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = viewState.value.isCheck,
                contentPadding = ButtonDefaults.ContentPadding
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google_logo),
                    contentDescription = "SignIn with Google",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = stringResource(id = R.string.auth_button))
            }
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun AuthPreview() {
    ScreenAuth(navController = rememberNavController())
}