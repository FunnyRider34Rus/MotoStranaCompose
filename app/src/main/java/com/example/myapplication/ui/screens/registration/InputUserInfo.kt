package com.example.myapplication.ui.screens.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.myapplication.database.firebase.*
import com.example.myapplication.ui.navigation.AuthScreen
import com.example.myapplication.ui.navigation.BottomNavItem
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.golbat_10
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

@Composable
fun InputUserInfo(
    navController: NavController,
    userPhoneNumber: String
) {

    var userFirstName by rememberSaveable { mutableStateOf("") }
    var userSecondName by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(null) {
        reAuthentication(navController, userPhoneNumber)
    }

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
                .clickable { navController.navigate(AuthScreen.InputSMSCode.route) }
        )
        Text(
            text = stringResource(R.string.input_user_info_title_text),
            modifier = Modifier.padding(top = 40.dp),
            style = MaterialTheme.typography.h1
        )
        OutlinedTextField(
            value = userFirstName, onValueChange = { inputCode ->
                userFirstName = inputCode
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
                    text = stringResource(R.string.input_user_info_user_first_name_hint)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = black,
                backgroundColor = golbat_10,
                focusedBorderColor = golbat_10,
                unfocusedBorderColor = golbat_10
            )
        )
        OutlinedTextField(
            value = userSecondName,
            onValueChange = { input ->
                userSecondName = input
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
                    text = stringResource(R.string.input_user_info_user_second_name_hint)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
                if (userFirstName.isNotBlank() && userSecondName.isNotBlank()) {
                    saveUserInfoToDB(userFirstName, userSecondName, userPhoneNumber)
                    navController.navigate(BottomNavItem.Dashboard.route)
                }
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

fun reAuthentication(navController: NavController, userPhoneNumber: String) {
    REMOTE_DATABASE.child(NODE_PHONES).addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.hasChild(userPhoneNumber)) {
                Thread.sleep(600L)
                navController.navigate(BottomNavItem.Dashboard.route)
            }
        }

        override fun onCancelled(error: DatabaseError) {

        }
    })
}

fun saveUserInfoToDB(userFirstName: String, userSecondName: String, phoneNumber: String) {
    val fullname = "$userFirstName $userSecondName"
    val currentUser = AUTH.currentUser?.uid
    REMOTE_DATABASE.child(NODE_USERS).child(currentUser.toString()).child(CHILD_ID)
        .setValue(currentUser)
    REMOTE_DATABASE.child(NODE_USERS).child(currentUser.toString()).child(CHILD_FULLNAME)
        .setValue(fullname)
    REMOTE_DATABASE.child(NODE_USERS).child(currentUser.toString()).child(CHILD_USERNAME)
        .setValue(currentUser)
    REMOTE_DATABASE.child(NODE_USERS).child(currentUser.toString()).child(CHILD_PHONE)
        .setValue(phoneNumber)
    REMOTE_DATABASE.child(NODE_PHONES).child(phoneNumber).setValue(currentUser)
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