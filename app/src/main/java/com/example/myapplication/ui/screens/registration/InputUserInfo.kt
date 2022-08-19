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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.database.*
import com.example.myapplication.ui.navigation.AuthScreen
import com.example.myapplication.ui.navigation.Graph
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.golbat_5

@Composable
fun InputUserInfo(
    navController: NavController,
    userPhoneNumber: String
) {

    var userFirstName by remember { mutableStateOf("") }
    var userSecondName by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_back),
            contentDescription = stringResource(R.string.button_back_description),
            modifier = Modifier
                .padding(24.dp, 32.dp, 0.dp, 0.dp)
                .height(24.dp)
                .width(24.dp)
                .clickable {  }
        )
        Text(
            text = stringResource(R.string.input_user_info_title_text),
            modifier = Modifier.padding(24.dp, 16.dp, 24.dp, 8.dp),
            style = MaterialTheme.typography.h1
        )
        OutlinedTextField(
            value = userFirstName, onValueChange = { inputCode ->
                userFirstName = inputCode
            },
            modifier = Modifier
                .padding(24.dp, 24.dp, 24.dp, 0.dp)
                .height(56.dp)
                .fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    style = MaterialTheme.typography.h3,
                    text = stringResource(R.string.input_user_info_user_first_name_hint)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = black,
                backgroundColor = golbat_5,
                focusedBorderColor = golbat_5,
                unfocusedBorderColor = golbat_5
            )
        )
        OutlinedTextField(
            value = userSecondName, onValueChange = { input ->
                userSecondName = input
            },
            modifier = Modifier
                .padding(24.dp, 24.dp, 24.dp, 0.dp)
                .height(56.dp)
                .fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    style = MaterialTheme.typography.h3,
                    text = stringResource(R.string.input_user_info_user_second_name_hint)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
            onClick = {
                if (userFirstName.isNotBlank() && userSecondName.isNotBlank()) {
                    saveUserInfoToDB(userFirstName, userSecondName, userPhoneNumber)
                    navController.navigate(Graph.MAIN)
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

fun saveUserInfoToDB(userFirstName: String, userSecondName: String, phoneNumber: String) {
    val fullname = "$userFirstName $userSecondName"
    val currentUser = AUTH.currentUser?.uid
    REMOTE_DATABASE.child(NODE_USERS).child(currentUser.toString()).child(CHILD_FULLNAME).setValue(fullname)
    REMOTE_DATABASE.child(NODE_USERS).child(currentUser.toString()).child(CHILD_PHONE).setValue(phoneNumber)
}

@Preview(showBackground = true)
@Composable
fun InputUserInfoPreview() {
    MyApplicationTheme {
        InputUserInfo(
            navController = rememberNavController(), "+7(xxx)xxx-xx-xx"
        )
    }
}