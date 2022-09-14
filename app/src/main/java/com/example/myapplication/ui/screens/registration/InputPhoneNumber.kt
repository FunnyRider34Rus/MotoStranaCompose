package com.example.myapplication.ui.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.myapplication.common.utils.startPhoneNumberVerification
import com.example.myapplication.ui.navigation.AuthScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.golbat_10

@Composable
fun InputPhoneNumber(navController: NavController) {

    var phone by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_back),
            contentDescription = stringResource(R.string.button_back_description),
            modifier = Modifier
                .padding(top = 32.dp)
                .size(24.dp)
                .clickable { navController.navigate(AuthScreen.Welcome.route) },
        )
        Text(
            text = stringResource(R.string.input_phone_number_title_text),
            modifier = Modifier.padding(top = 40.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h1
        )
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .width(56.dp)
                    .height(56.dp)
                    .background(golbat_10, MaterialTheme.shapes.large),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.input_phone_number_country_code),
                    color = black,
                    style = MaterialTheme.typography.h3
                )
            }
            OutlinedTextField(
                value = phone,
                onValueChange = { newText ->
                    phone = newText
                },
                modifier = Modifier
                    .height(56.dp)
                    .padding(start = 24.dp),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                placeholder = {
                    Text(
                        text = stringResource(R.string.input_phone_number_hint_text),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        style = MaterialTheme.typography.h3
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    goToScreenForInputCode(navController, phone)
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
        }
        Button(
            onClick = {
                goToScreenForInputCode(navController, phone)
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

fun goToScreenForInputCode(navController: NavController, phone: String) {
    val mPhone = phone.filter { it.isDigit() }
    if (mPhone.length == 10) {
        val userPhoneNumber = "+7$mPhone"
        startPhoneNumberVerification(userPhoneNumber)
        navController.navigate(route = AuthScreen.InputSMSCode.route + "/$userPhoneNumber")
    }
}

@Preview(showBackground = true)
@Composable
fun InputPhoneNumberPreview() {
    MyApplicationTheme {
        InputPhoneNumber(
            navController = rememberNavController()
        )
    }
}


