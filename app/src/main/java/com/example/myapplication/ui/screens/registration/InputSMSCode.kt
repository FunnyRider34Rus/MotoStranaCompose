package com.example.myapplication.ui.screens.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.common.utils.verifyPhoneNumberWithCode
import com.example.myapplication.ui.navigation.AuthScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.golbat_10
import kotlinx.coroutines.*

@Composable
fun InputSMSCode(
    navController: NavController,
    userPhoneNumber: String
) {

    var code by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_back),
            contentDescription = stringResource(R.string.button_back_description),
            modifier = Modifier
                .padding(top = 32.dp)
                .size(24.dp)
                .clickable { navController.navigate(AuthScreen.InputUserPhone.route) }
        )
        Text(
            text = stringResource(R.string.input_sms_code_title_text),
            modifier = Modifier.padding(top = 40.dp),
            style = MaterialTheme.typography.h1
        )
        Text(
            text = stringResource(R.string.input_sms_code_body_text) + " " + userPhoneNumber,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            style = MaterialTheme.typography.h3
        )
        OutlinedTextField(
            value = code, onValueChange = { inputCode ->
                code = inputCode
            },
            modifier = Modifier
                .padding(top = 24.dp)
                .height(56.dp)
                .fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    style = MaterialTheme.typography.h3,
                    text = stringResource(R.string.input_sms_code_hint)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                goToScreenForInputUserInfo(navController, code, userPhoneNumber)
            }),
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = black,
                backgroundColor = golbat_10,
                focusedBorderColor = golbat_10,
                unfocusedBorderColor = golbat_10
            )
        )
        Button(
            onClick = {
                goToScreenForInputUserInfo(navController, code, userPhoneNumber)
            },
            modifier = Modifier
                .padding(top = 72.dp)
                .height(56.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large
        ) {
            Text(
                text = stringResource(R.string.button_next_text),
                style = MaterialTheme.typography.button
            )
        }
    }
}

fun goToScreenForInputUserInfo(
    navController: NavController,
    code: String,
    userPhoneNumber: String
) = runBlocking {
    val mCode = code.filter { it.isDigit() }
    if (mCode.length == 6) {
        launch {
            verifyPhoneNumberWithCode(mCode)
        }
        launch {
            delay(600)
            navController.navigate(route = AuthScreen.InputUserInfo.route + "/$userPhoneNumber")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun InputSMSCodePreview() {
    MyApplicationTheme {
        InputSMSCode(
            navController = rememberNavController(), "+7(xxx)xxx-xx-xx"
        )
    }
}