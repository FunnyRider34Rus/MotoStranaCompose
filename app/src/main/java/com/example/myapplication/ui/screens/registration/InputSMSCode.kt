package com.example.myapplication.ui.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.screens.Navigate
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.golbat_5
import com.example.myapplication.ui.theme.golbat_60

@Composable
fun InputSMSCode(navController: NavController, userPhoneNumber: String) {

    var code by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_back),
            contentDescription = stringResource(R.string.button_back_description),
            modifier = Modifier
                .padding(24.dp, 32.dp, 0.dp, 0.dp)
                .height(24.dp)
                .width(24.dp)
                .clickable { navController.navigate(Navigate.InputPhoneNumber.route) }
        )
        Text(
            text = stringResource(R.string.input_sms_code_title_text),
            modifier = Modifier.padding(24.dp, 16.dp, 24.dp, 8.dp),
            style = MaterialTheme.typography.h1
        )
        Text(
            text = stringResource(R.string.input_sms_code_body_text) + userPhoneNumber,
            modifier = Modifier
                .padding(24.dp, 0.dp, 24.dp, 0.dp)
                .fillMaxWidth(),
            color = golbat_60,
            style = MaterialTheme.typography.h3
        )
        OutlinedTextField(value = code, onValueChange = { inputCode ->
            code = inputCode
        },
            modifier = Modifier.padding(24.dp,24.dp,24.dp,0.dp)
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = black,
                backgroundColor = golbat_5,
                focusedBorderColor = golbat_5,
                unfocusedBorderColor = golbat_5
            )
        )
        Button(
            onClick = { navController.navigate("input_sms_code") },
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
fun InputSMSCodePreview() {
    MyApplicationTheme {
        InputSMSCode(
            navController = rememberNavController(), ""
        )
    }
}