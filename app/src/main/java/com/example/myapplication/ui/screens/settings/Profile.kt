package com.example.myapplication.ui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.common.TextInput
import com.example.myapplication.database.firebase.*
import com.example.myapplication.ui.navigation.BottomNavItem
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Profile(navController: NavController) {

    val scope = rememberCoroutineScope()

    val displayUserName =
        if (USER.username.isBlank() || USER.username == AUTH.currentUser?.uid) {
            stringResource(id = R.string.input_user_info_user_name_hint)
        } else {
            USER.username
        }

    val displayUserBike =
        USER.bike.ifBlank { stringResource(id = R.string.input_user_info_user_bike_hint) }

    val fullnameList = USER.fullname.split(" ")
    var userFirstName by rememberSaveable { mutableStateOf(fullnameList[0]) }
    var userSecondName by rememberSaveable { mutableStateOf(fullnameList[1]) }
    var userName by rememberSaveable { mutableStateOf(displayUserName) }
    var userBike by rememberSaveable { mutableStateOf(displayUserBike) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_back),
                contentDescription = stringResource(R.string.button_back_description),
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate(BottomNavItem.Settings.route)
                    },
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_edit),
                contentDescription = stringResource(R.string.button_edit_description),
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        scope.launch {
                            val currentID = AUTH.currentUser?.uid
                            if (userFirstName.isNotBlank() && userSecondName.isNotBlank()) {
                                val fullname = "$userFirstName $userSecondName"
                                REMOTE_DATABASE
                                    .child(NODE_USERS)
                                    .child(currentID.toString())
                                    .child(CHILD_FULLNAME)
                                    .setValue(fullname)
                            }

                            if (userName.isNotBlank()) {
                                REMOTE_DATABASE
                                    .child(NODE_USERS)
                                    .child(currentID.toString())
                                    .child(CHILD_USERNAME)
                                    .setValue(userName)
                                REMOTE_DATABASE
                                    .child(NODE_NICKNAMES)
                                    .child(userName)
                                    .setValue(currentID)
                            }
                            if (userBike.isNotBlank()) {
                                REMOTE_DATABASE
                                    .child(NODE_USERS)
                                    .child(currentID.toString())
                                    .child(CHILD_BIKE)
                                    .setValue(userBike)
                            }
                        }
                        navController.navigate(BottomNavItem.Settings.route)
                    },
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Профиль",
                modifier = Modifier.padding(top = 40.dp),
                style = MaterialTheme.typography.h1
            )
        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/User_font_awesome.svg/512px-User_font_awesome.svg.png?20160212005950")
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_no_profile),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(shape = MaterialTheme.shapes.small)
                )
            }
            TextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                value = userFirstName,
                hint = userFirstName,
                onValueChange = { userFirstName = it })
            TextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                value = userSecondName,
                hint = userSecondName,
                onValueChange = { userSecondName = it })
            TextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                value = userName,
                hint = userName,
                onValueChange = { userName = it })
            TextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                value = userBike,
                hint = userBike,
                onValueChange = { userBike = it })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MyApplicationTheme {
        Profile(navController = rememberNavController())
    }
}