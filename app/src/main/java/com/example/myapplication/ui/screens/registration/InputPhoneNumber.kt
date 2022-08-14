package com.example.myapplication.ui.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.screens.registration.navigation.Screen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.golbat_5
import com.example.myapplication.utils.startPhoneNumberVerification

@Composable
fun InputPhoneNumber(navController: NavController) {

    var phone by remember { mutableStateOf("") }
    var userPhoneNumber: String

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_back),
            contentDescription = stringResource(R.string.button_back_description),
            modifier = Modifier
                .padding(24.dp, 32.dp, 0.dp, 0.dp)
                .height(24.dp)
                .width(24.dp)
                .clickable { navController.navigate(Screen.Welcome.route) },
        )
        Text(
            text = stringResource(R.string.input_phone_number_title_text),
            modifier = Modifier.padding(24.dp, 16.dp, 24.dp, 32.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h1
        )
        Row(
            modifier = Modifier
                .height(56.dp)
                .padding(24.dp, 0.dp, 24.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .width(56.dp)
                    .height(56.dp)
                    .background(golbat_5, MaterialTheme.shapes.large),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.input_phone_number_country_code),
                    style = MaterialTheme.typography.h3
                )
            }
            OutlinedTextField(
                value = phone,
                onValueChange = { newText ->
                    phone = newText
                },
                modifier = Modifier
                    .padding(24.dp, 0.dp, 0.dp, 0.dp)
                    .height(56.dp),
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = black,
                    backgroundColor = golbat_5,
                    focusedBorderColor = golbat_5,
                    unfocusedBorderColor = golbat_5
                )
            )
        }
        Button(
            onClick = {
                phone = phone.filter { it.isDigit() }
                if (phone.length == 10) {
                    userPhoneNumber = "+7$phone"
                    startPhoneNumberVerification(userPhoneNumber)
                    navController.navigate(Screen.InputSMSCode.withArgs(userPhoneNumber))
                }
            },
            modifier = Modifier
                .padding(24.dp, 72.dp, 24.dp, 0.dp)
                .height(56.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large
        ) {
            Text(
                text = stringResource(R.string.button_next_text),
                style = MaterialTheme.typography.h3
            )
        }
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


